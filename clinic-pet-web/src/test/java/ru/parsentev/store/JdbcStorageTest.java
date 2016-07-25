package ru.parsentev.store;

import org.junit.Test;
import ru.parsentev.models.Role;
import ru.parsentev.models.User;

import static org.junit.Assert.assertEquals;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 29.04.2015
 */
public class JdbcStorageTest {

    @Test
    public void testCreate() throws Exception {
        final String name = "test";
        final String email = "test@test.com";

        Role role = new Role();
        role.setName(name);
        role.setId(2);
        User user = new User(-1, name, email);
        user.setRole(role);

        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(user);
        final User userActual = storage.get(id);

        assertEquals(id, userActual.getId());
        assertEquals(name, userActual.getLogin());
        assertEquals(email, userActual.getEmail());
        assertEquals(role.getId(), userActual.getRole().getId());
        storage.close();
    }

    @Test
    public void testEdit() throws Exception {
        final String name = "test";
        final String email = "test@test.com";

        Role role = new Role();
        role.setName(name);
        role.setId(2);
        User user = new User(-1, name, email);
        user.setRole(role);

        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(user);
        user.setId(id);
        user.setLogin(name + "2");
        user.setEmail(email + "2");
        user.getRole().setId(1);
        storage.edit(user);
        final User userActual = storage.get(id);

        assertEquals(id, userActual.getId());
        assertEquals(name + "2", userActual.getLogin());
        assertEquals(email + "2", userActual.getEmail());
        assertEquals(role.getId(), userActual.getRole().getId());
        storage.close();
    }

    @Test(expected = IllegalStateException.class)
    public void testDelete() throws Exception {
        final String name = "test";
        final String email = "test@test.com";

        Role role = new Role();
        role.setName(name);
        role.setId(2);
        User user = new User(-1, name, email);
        user.setRole(role);

        final JdbcStorage storage = new JdbcStorage();
        final int id = storage.add(user);
        storage.delete(id);

        final User userActual = storage.get(id);
        storage.close();
    }

    @Test
    public void testFindByLogin() throws Exception {
        final String login = "test";
        final JdbcStorage storage = new JdbcStorage();
        User userActual = storage.findByLogin(login);
        assertEquals(login, userActual.getLogin());
    }
}
