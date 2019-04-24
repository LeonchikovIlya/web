package by.epam.webproject.entity;

import java.util.Objects;

/**
 * manager
 */

public class Administrator extends User {

    private int id;
    private String name;
    private String surname;
    private User userId;

    public Administrator() {
    }

    public Administrator(String name, String surname, User userId) {
        this.name = name;
        this.surname = surname;
        this.userId = userId;
    }

    public Administrator(int id, String name, String surname, User userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname != null) {
            this.surname = surname;
        }
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        if (userId != null) {
            this.userId = userId;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Administrator administrator = (Administrator) o;
        return id == administrator.id &&
                Objects.equals(name, administrator.name) &&
                Objects.equals(surname, administrator.surname) &&
                Objects.equals(userId, administrator.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, userId);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userId=" + userId +
                '}';
    }
}
