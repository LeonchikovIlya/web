package by.epam.webproject.dao;

import by.epam.webproject.dao.exception.DAOException;

import java.util.List;

public interface CrudDao<T> {

    T create(T entity) throws DAOException;

    void update(T entity);

    void delete(T entity) throws DAOException;

    T find(int id) throws DAOException;

    List<T> findAll() throws DAOException;


}
