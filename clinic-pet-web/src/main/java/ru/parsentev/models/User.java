package ru.parsentev.models;

import java.util.Set;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 17.04.2015
 */
public class User extends Base {

    private String login;
    private String email;
    private Role role = new Role();
    private Set<Message> messages;

    public User() {
        //empty
    }

    public User(final int id, final String login, final String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
