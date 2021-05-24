package by.sample.dao;

import by.sample.entity.Operation;
import by.sample.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryHistoryDAO implements HistoryDAO{
    private final List<Operation> operationList = new ArrayList<>();
    private long nextId = 1;

    @Override
    public long save(Operation operation) {
        operation.setId(nextId++);
        operationList.add(operation);
        return operation.getId();
    }

    @Override
    public List<Operation> getAll() {
        return new ArrayList<>(operationList);
    }

    @Override
    public List<Operation> getAll(User user) {
        List<Operation> operationUser = new ArrayList<>();

        for (Operation operation : operationList) {
            if (operation.getUser().equals(user)) {
                operationUser.add(operation);
            }
        }

        return operationUser;
    }
}
