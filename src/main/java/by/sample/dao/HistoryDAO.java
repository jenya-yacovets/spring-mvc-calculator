package by.sample.dao;

import by.sample.entity.Operation;
import by.sample.entity.User;

import java.util.List;

public interface HistoryDAO {
    long save(Operation operation);
    List<Operation> getAll();
    List<Operation> getAll(User user);
}
