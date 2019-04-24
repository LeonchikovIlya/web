package by.epam.webproject.dao;

import by.epam.webproject.entity.User;

public interface UserDao extends CrudDao<User> {

    User findByLogin(String login);

}
