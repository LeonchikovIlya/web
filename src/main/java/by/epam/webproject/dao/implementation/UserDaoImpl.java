package by.epam.webproject.dao.implementation;

import by.epam.webproject.dao.UserDao;
import by.epam.webproject.dao.exception.DAOException;
import by.epam.webproject.dao.pool.ConnectionPool;
import by.epam.webproject.entity.Customer;
import by.epam.webproject.entity.Administrator;
import by.epam.webproject.entity.User;
import by.epam.webproject.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User create(User entity) throws DAOException {

        //language=SQL
        String insertUser = "INSERT INTO user (login, password, email, user_role_id_user_role) VALUES (?,?,?,?)";

        User user = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)
        ) {
            if (entity != null) {
                addDataToEntity(entity, statement);
                statement.executeUpdate();

                try (ResultSet rs = statement.getGeneratedKeys()) {

                    if (rs.next()) {
                        int newId = rs.getInt(1);
                        user = new User(newId, entity.getLogin(), entity.getPassword()
                                , entity.getEmail(), entity.getUserRole());
                    } else {
                        throw new DAOException("Generated key set is empty");
                    }
                }
            } else {
                System.out.println("Cannot create null entity");
            }
        } catch (SQLException e) {
            throw new DAOException("Connection error\n" + e);
        }
        System.out.println(user);
        return user;
    }

    private void addDataToEntity(User entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getEmail());
        statement.setInt(4, entity.getUserRole().getId());
    }

    @Override
    public void update(User entity) {


        //language=SQL
        String updateUser = "UPDATE user SET login = ?, password = ?, email = ?,user_role_id_user_role = ?" +
                " WHERE id_user = ?";

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateUser
                     , ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {

            addDataToEntity(entity, statement);
            statement.setInt(5, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * The user will be deleted from database table 'user' with calling
     * methods {@link CustomerDaoImpl#delete(Customer)} and {@link ManagerDaoImpl#delete(Administrator)}.
     */
    @Override
    public void delete(User entity) throws DAOException {
        throw new DAOException("No implementation of this method");
    }

    @Override
    public User find(int id) throws DAOException {

        //language=SQL
        String findUser = "SELECT login,password,email,user_role_id_user_role,ur.name " +
                "FROM user INNER JOIN user_role ur " +
                "ON user.user_role_id_user_role = ur.id_user_role " +
                "WHERE user.id_user = " + id;

        User user = null;

        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findUser)
        ) {
            if (rs.next()) {
                String userLogin = rs.getString(1);
                String userPassword = rs.getString(2);
                String userEmail = rs.getString(3);
                int userRoleId = rs.getInt(4);
                String userRoleName = rs.getString(5);

                UserRole userRole = new UserRole(userRoleId, userRoleName);

                user = new User(id, userLogin, userPassword, userEmail, userRole);

            } else {
                throw new DAOException("Cannot find user with such id. Id = " + id + ".");
            }
        } catch (SQLException e) {
            throw new DAOException("Connection error\n" + e);
        }
        System.out.println(user);
        return user;
    }


    @Override
    public User findByLogin(String login) {

        //language=SQL
        String findUserByLogin = "SELECT id_user,login,password,email,user_role_id_user_role,ur.name" +
                " FROM user INNER JOIN user_role ur" +
                " ON user.user_role_id_user_role = ur.id_user_role WHERE login = ?";

        User user = null;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(findUserByLogin
                     , ResultSet.TYPE_SCROLL_INSENSITIVE
                     , ResultSet.CONCUR_UPDATABLE);
        ) {
            statement.setString(1, login);

            List<User> users = getUsers(statement);
            if (users.size() > 0) {
                user = users.get(0);
            } else {
                System.out.println("There is no user with such id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {

        //language=SQL
        String findAllUsers = "SELECT id_user,login,password,email,user_role_id_user_role,ur.name " +
                "FROM user INNER JOIN user_role ur " +
                "ON user.user_role_id_user_role = ur.id_user_role";

        List<User> list;

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllUsers
                     , ResultSet.TYPE_SCROLL_INSENSITIVE
                     , ResultSet.CONCUR_UPDATABLE);
        ) {

            list = getUsers(statement);

        } catch (SQLException e) {
            throw new DAOException("Connection error\n" + e);
        }
        System.out.println(list);
        return list;
    }

    private List<User> getUsers(PreparedStatement statement) {

        List<User> users = new ArrayList<>();

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt(1);
                String userLogin = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                int userRoleId = rs.getInt(5);
                String userRoleName = rs.getString(6);

                UserRole userRole = new UserRole(userRoleId, userRoleName);

                users.add(new User(userId, userLogin, password, email, userRole));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
