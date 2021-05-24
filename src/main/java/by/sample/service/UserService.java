package by.sample.service;

import by.sample.dao.UserDAO;
import by.sample.entity.User;
import by.sample.exception.InvalidLoginOrPasswordException;
import by.sample.exception.LoginIsBusyException;
import by.sample.exception.dao.DataNotFoundDAOException;
import by.sample.exception.dao.DuplicationDataDAOException;
import by.sample.model.UserLoginModel;
import by.sample.model.UserRegisterModel;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void register(UserRegisterModel user) throws LoginIsBusyException {
        try {
            userDAO.save(new User(user.getName(), user.getLogin(), user.getPassword()));
        } catch (DuplicationDataDAOException duplicationDataDAOException) {
            throw new LoginIsBusyException();
        }
    }

    public User login(UserLoginModel user) throws InvalidLoginOrPasswordException {
        try {
            User findUser = userDAO.findByLogin(user.getLogin());
            if(findUser.getPassword().equals(user.getPassword())) {
                return findUser;
            } else {
                throw new InvalidLoginOrPasswordException();
            }
        } catch (DataNotFoundDAOException e) {
            throw new InvalidLoginOrPasswordException();
        }
    }
}
