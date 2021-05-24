package by.sample.dao;

import by.sample.entity.User;
import by.sample.exception.dao.DataNotFoundDAOException;
import by.sample.exception.dao.DuplicationDataDAOException;

public interface UserDAO {
    long save(User user) throws DuplicationDataDAOException;
    User findByLogin(String login) throws DataNotFoundDAOException;
}
