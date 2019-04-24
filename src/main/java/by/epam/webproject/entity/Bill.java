package by.epam.webproject.entity;

import java.util.Objects;


/**
 * bill
 */
public class Bill {

    private int id;
    private boolean paid;
    private int sum;
    private Order orderId;

    public Bill() {
    }

    public Bill(boolean paid, int sum, Order orderId) {
        this.paid = paid;
        this.sum = sum;
        this.orderId = orderId;
    }

    public Bill(int id, boolean paid, int sum, Order orderId) {
        this.id = id;
        this.paid = paid;
        this.sum = sum;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Bill bill = (Bill) o;
        return id == bill.id &&
                paid == bill.paid &&
                sum == bill.sum &&
                Objects.equals(orderId, bill.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paid, sum, orderId);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", paid=" + paid +
                ", sum=" + sum +
                ", orderId=" + orderId +
                '}';
    }
}
