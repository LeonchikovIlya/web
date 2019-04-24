package by.epam.webproject.entity;

import java.util.Objects;

/**
 * customer
 */

public class Customer extends User {

    private int id;
    private String name;
    private String phone;
    private User userId;

    public Customer() {
    }

    public Customer(String name, String phone, User userId) {
        this.name = name;
        this.phone = phone;
        this.userId = userId;
    }

    public Customer(int id, String name, String phone, User userId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null) {
            this.phone = phone;
        }
    }

    public User getUser() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User userId) {
        if (userId != null) {
            this.userId = userId;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(userId, customer.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, userId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", userId = [" + userId + "]" +
                '}';
    }
}
