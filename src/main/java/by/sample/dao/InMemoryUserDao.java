package by.sample.dao;

import by.sample.entity.User;
import by.sample.exception.dao.DataNotFoundDAOException;
import by.sample.exception.dao.DuplicationDataDAOException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryUserDao implements UserDAO{
    private final List<User> userList = new ArrayList<>();
    private long nextId = 1;

    @Override
    public long save(User user) throws DuplicationDataDAOException {
        for(User item : userList) {
            if(item.getLogin().equals(user.getLogin())) throw new DuplicationDataDAOException("Login is busy");
        }
        user.setId(nextId++);
        userList.add(user);
        return user.getId();
    }

    @Override
    public User findByLogin(String login) throws DataNotFoundDAOException {
        for(User item : userList) {
            if(item.getLogin().equals(login)) {
                return item;
            }
        }
        throw new DataNotFoundDAOException("User not found");
    }
}
