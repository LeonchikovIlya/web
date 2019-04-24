package by.epam.webproject.entity;

import java.util.Objects;

/**
 * users role
 */
public class UserRole {

    private int id;
    private String name;

    public UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

    public UserRole(int id) {
        this.id = id;
    }

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return id == userRole.id &&
                Objects.equals(name, userRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserRole :" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
