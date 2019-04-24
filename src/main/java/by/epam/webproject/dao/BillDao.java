package by.epam.webproject.dao;

import by.epam.webproject.entity.Bill;

public interface BillDao extends CrudDao<Bill> {

    Bill findByOrderId(int orderId);

}
