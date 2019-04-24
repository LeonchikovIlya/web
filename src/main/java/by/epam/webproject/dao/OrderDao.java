package by.epam.webproject.dao;

import by.epam.webproject.entity.Order;
import by.epam.webproject.entity.Status;

import java.util.List;

public interface OrderDao extends CrudDao<Order> {

    Order findByCustomerId(int customerId);

    List<Order> findByStatus(Status status);
}
