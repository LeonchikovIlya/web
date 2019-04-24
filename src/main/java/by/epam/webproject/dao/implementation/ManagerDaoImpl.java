package by.epam.webproject.dao.implementation;

import by.epam.webproject.dao.CrudDao;
import by.epam.webproject.dao.exception.DAOException;
import by.epam.webproject.dao.pool.ConnectionPool;
import by.epam.webproject.entity.Customer;
import by.epam.webproject.entity.Administrator;
import by.epam.webproject.entity.User;

import java.sql.*;
import java.util.List;

public class ManagerDaoImpl implements CrudDao<User> {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Administrator create(User entity) throws DAOException {

        //language=SQL
        String insertUser = "INSERT INTO administrator (name, surname, user_id_user) VALUES (?,?,?)";

        Administrator administrator = (Administrator) entity;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)
        ) {
            if (entity != null) {
                statement.setString(1,((Administrator) entity).getName());
                statement.setString(2,((Administrator) entity).getSurname());
                System.out.println("USER ID");
                statement.setInt(3,((Administrator) entity).getUserId().getId());
                System.out.println(((Administrator) entity).getUserId().getId());
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {

                    if (rs.next()) {
                        int newId = rs.getInt(1);
                        System.out.println(((Administrator) entity).getUserId());
                        administrator = new Administrator(newId, ((Administrator) entity).getName(), ((Administrator) entity).getSurname()
                                , ((Administrator) entity).getUserId());
                    } else {
                        throw new DAOException("Generated key set is empty");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Connection error\n" + e);
        }
        return administrator;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) throws DAOException {

    }

    @Override
    public User find(int id) throws DAOException {
        return null;
    }

    @Override
    public List<User> findAll() throws DAOException {
        return null;
    }
}
