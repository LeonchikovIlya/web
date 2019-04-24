package by.epam.webproject.entity;

import java.util.Objects;


/**
 * user
 */
public class User {

    public static final int CUSTOMER_ROLE = 1;

    public static final int ADMINISTRATOR_ROLE = 2;

    private int id;
    private String login;
    private String password;
    private String email;
    private UserRole userRoleId;

    public User() {
    }

    public User(String login, String password, String email, UserRole userRoleId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRoleId = userRoleId;
    }

    public User(int id, String login, String password, String email, UserRole userRoleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRoleId = userRoleId;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null) {
            this.login = login;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    public UserRole getUserRole() {
        return userRoleId;
    }

    public int getUserRoleId() {
        return userRoleId.getId();
    }

    public void setUserRoleId(UserRole userRoleId) {
        if (userRoleId != null) {
            this.userRoleId = userRoleId;
        }
    }

    /**
     * define user opportunities
     */

    public boolean isAdmin() {
        return getUserRoleId() == ADMINISTRATOR_ROLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userRoleId, user.userRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, userRoleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRoleId=" + userRoleId +
                '}';
    }
}
