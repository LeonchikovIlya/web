package by.epam.webproject.dao.implementation;

import by.epam.webproject.dao.CrudDao;
import by.epam.webproject.dao.UserDao;
import by.epam.webproject.dao.exception.DAOException;
import by.epam.webproject.dao.pool.ConnectionPool;
import by.epam.webproject.entity.Customer;
import by.epam.webproject.entity.User;
import by.epam.webproject.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CrudDao<Customer> {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Customer create(Customer entity) throws DAOException {

        //language=SQL
        String insertUser = "INSERT INTO customer (name, phone, user_id_user) VALUES (?,?,?)";

        Customer customer = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)
        ) {
            if (entity != null) {
                statement.setString(1,entity.getName());
                statement.setString(2,entity.getPhone());
                System.out.println("USER ID");
                statement.setInt(3,entity.getUser().getId());
                System.out.println(entity.getUser().getId());
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {

                    if (rs.next()) {
                        int newId = rs.getInt(1);
                        System.out.println(entity.getUser());
                        customer = new Customer(newId, entity.getName(), entity.getPhone()
                                , entity.getUser());
                    } else {
                        throw new DAOException("Generated key set is empty");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Connection error\n" + e);
        }
        return customer;
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(Customer entity) throws DAOException {

        //language=SQL
        String deleteCustomer = "DELETE FROM customer" +
                " WHERE user_id_user = ?";

        //language=SQL
        String SQL_DELETE_USER = "DELETE FROM user WHERE id_user = ?";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteCustomer
                     , ResultSet.TYPE_SCROLL_INSENSITIVE
                     , ResultSet.CONCUR_UPDATABLE);
             PreparedStatement statement2 = connection.prepareStatement(SQL_DELETE_USER
                     , ResultSet.TYPE_SCROLL_INSENSITIVE
                     , ResultSet.CONCUR_UPDATABLE)
        ) {
            if (entity != null) {
                statement.setInt(1, entity.getUser().getId());
                statement2.setInt(1, entity.getUser().getId());
                statement.executeUpdate();
                statement2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer find(int id) throws DAOException {

        //language=SQL
        String findById = "SELECT id_customer,customer.name,phone,user_id_user,user.login" +
                ",user.password,user.email,user.user_role_id_user_role,user_role.name" +
                " FROM customer INNER JOIN user ON user_id_user = id_user INNER JOIN user_role ON " +
                "id_user_role=user_role_id_user_role WHERE id_customer = ?";

        Customer customer = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(findById
                     , ResultSet.TYPE_SCROLL_INSENSITIVE
                     , ResultSet.CONCUR_UPDATABLE)) {

            statement.setInt(1, id);
            List<Customer> customers = getCustomers(statement);

            if (customers.size() > 0) {
                customer = customers.get(0);
            } else {
                System.out.println("there is no customer with such id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public List<Customer> findAll() throws DAOException {

        //language=SQL
        String findById = "SELECT id_customer,customer.name,phone,user_id_user,user.login" +
                ",user.password,user.email,user.user_role_id_user_role,user_role.name" +
                " FROM customer INNER JOIN user ON user_id_user = id_user" +
                " INNER JOIN user_role ON id_user_role=user_role_id_user_role";

        List<Customer> customers = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(findById
                     , ResultSet.TYPE_SCROLL_INSENSITIVE
                     , ResultSet.CONCUR_UPDATABLE)) {

            customers = getCustomers(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private List<Customer> getCustomers(PreparedStatement statement) {

        List<Customer> customers = new ArrayList<>();

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int customerId = rs.getInt(1);
                String customerName = rs.getString(2);
                String customerPhone = rs.getString(3);
                int userId = rs.getInt(4);
                String userLogin = rs.getString(5);
                String userPassword = rs.getString(6);
                String userEmail = rs.getString(7);
                int userRoleId = rs.getInt(8);
                String userRoleName = rs.getString(9);

                UserRole userRole = new UserRole(userRoleId, userRoleName);

                User user = new User(userId, userLogin, userPassword, userEmail, userRole);

                customers.add(new Customer(customerId, customerName, customerPhone, user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
