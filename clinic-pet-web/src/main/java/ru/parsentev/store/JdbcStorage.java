package ru.parsentev.store;

import ru.parsentev.models.Role;
import ru.parsentev.models.User;
import ru.parsentev.service.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 29.04.2015
 */
public class JdbcStorage implements Storage {

    private final Connection connection;

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<User> values() {
        final List<User> users = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                User user = new User(rs.getInt("uid"), rs.getString("login"), rs.getString("email"));
                user.setRole(getRole(rs.getInt("role_id")));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int add(User user) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO users (login, email, role_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getRole().getId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public void edit(User user) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE users SET login = (?), email = (?), role_id = (?) WHERE uid = (?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getRole().getId());
            statement.setInt(4, user.getId());
            if (statement.executeUpdate() > 0) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %d does not edited", user.getId()));
    }

    @Override
    public void delete(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM users WHERE uid = (?)")) {
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %d does not deleted", id));
    }

    @Override
    public User get(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM users WHERE uid=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User(rs.getInt("uid"), rs.getString("login"), rs.getString("email"));
                    user.setRole(getRole(rs.getInt("role_id")));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %d does not exists", id));
    }

    @Override
    public User findByLogin(String login) {
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "SELECT uid FROM users WHERE login=(?)")) {
            statement.setString(1, login);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return get(Integer.parseInt(rs.getString("uid")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User %s does not find", login));
    }

    @Override
    public int generateId() {
        return 0;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Role getRole(int id) throws SQLException {
        Role role = new Role();
        try (final PreparedStatement statementRole = this.connection.prepareStatement(
                "SELECT * FROM roles WHERE uid=(?)")) {
            statementRole.setInt(1, id);
            try (final ResultSet rsRole = statementRole.executeQuery()) {
                if (rsRole.next()) {
                    role.setId(rsRole.getInt("uid"));
                    role.setName(rsRole.getString("name"));
                }
            }
        }
        return role;
    }
}
