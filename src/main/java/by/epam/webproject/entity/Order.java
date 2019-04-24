package by.epam.webproject.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * order
 */

public class Order {

    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Customer customerId;
    private Administrator administratorId;
    private Status statusId;

    public Order() {
    }

    public Order(LocalDateTime startTime, LocalDateTime endTime
            , Customer customerId, Administrator administratorId, Status statusId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.administratorId = administratorId;
        this.statusId = statusId;
    }

    public Order(int id, LocalDateTime startTime, LocalDateTime endTime
            , Customer customerId, Administrator administratorId, Status statusId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.administratorId = administratorId;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        if (startTime != null) {
            this.startTime = startTime;
        }
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        if (endTime != null) {
            this.endTime = endTime;
        }
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        if (customerId != null) {
            this.customerId = customerId;
        }
    }

    public Administrator getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Administrator administratorId) {
        if (administratorId != null) {
            this.administratorId = administratorId;
        }
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        if (statusId != null) {
            this.statusId = statusId;
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
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(startTime, order.startTime) &&
                Objects.equals(endTime, order.endTime) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(administratorId, order.administratorId) &&
                statusId == order.statusId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, customerId, administratorId, statusId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", customerId=" + customerId +
                ", administratorId=" + administratorId +
                ", statusId=" + statusId +
                '}';
    }
}
