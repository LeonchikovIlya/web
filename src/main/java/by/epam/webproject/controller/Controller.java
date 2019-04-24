package by.epam.webproject.controller;

import by.epam.webproject.dao.exception.DAOException;
import by.epam.webproject.dao.implementation.CustomerDaoImpl;
import by.epam.webproject.dao.implementation.ManagerDaoImpl;
import by.epam.webproject.dao.implementation.UserDaoImpl;
import by.epam.webproject.dao.pool.ConnectionPool;
import by.epam.webproject.dao.pool.PooledConnection;
import by.epam.webproject.dao.pool.dbManager.DBManager;
import by.epam.webproject.entity.Customer;
import by.epam.webproject.entity.User;
import by.epam.webproject.entity.UserRole;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {

    public static void main(String[] args) {

       /* System.out.println(DBManager.getValue(DBManager.USER));
        System.out.println(DBManager.getValue(null));

        System.out.println(ConnectionPool.getInstance().getAvailableConnections());
        Connection conn = ConnectionPool.getInstance().getConnection();
        System.out.println(ConnectionPool.getInstance().getAvailableConnections());
        ConnectionPool.getInstance().returnConnection(conn);
        System.out.println(ConnectionPool.getInstance().getAvailableConnections());

        ConnectionPool.getInstance().releasePool();

        for (int i = 0;i< ConnectionPool.getInstance().getPoolSize();i++) {

            PooledConnection connection = new PooledConnection(ConnectionPool.getInstance().getAllConnections().poll());
            try {
                System.out.println(connection.isClosed());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }*/

        UserRole role = new UserRole(1,"customer");

        User user = new User("буря морей", "checkIt32", "l445вввы", role);


        ManagerDaoImpl managerDao = new ManagerDaoImpl();

        try {
            managerDao.create(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }


      /*  UserDaoImpl userDao = new UserDaoImpl();

        User newUser = null;

        try {
            newUser = userDao.create(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer("Vasiliy","+375445595708", newUser);

        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        try {
            customerDao.create(customer);
        } catch (DAOException e) {
            e.printStackTrace();
        }*/





      /*  CustomerDaoImpl customerDao = new CustomerDaoImpl();
        try {
            Customer customer = customerDao.find(8);
            customerDao.delete(customer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
*/

    }
}
