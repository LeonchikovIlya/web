package by.epam.webproject.entity;

import java.util.Objects;

/**
 * Relationship between Order and Menu
 */
public class OrderMenu {

    private int id;
    private Order orderId;
    private Menu menuId;

    public OrderMenu() {
    }

    public OrderMenu(Order orderId, Menu menuId) {
        this.id = id;
        this.orderId = orderId;
        this.menuId = menuId;
    }

    public OrderMenu(int id, Order orderId, Menu menuId) {
        this.id = id;
        this.orderId = orderId;
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Menu getMenuId() {
        return menuId;
    }

    public void setMenuId(Menu menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderMenu orderMenu = (OrderMenu) o;
        return id == orderMenu.id &&
                Objects.equals(orderId, orderMenu.orderId) &&
                Objects.equals(menuId, orderMenu.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, menuId);
    }
}
