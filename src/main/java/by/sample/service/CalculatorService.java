package by.sample.service;

import by.sample.config.OperationList;
import by.sample.dao.HistoryDAO;
import by.sample.entity.Operation;
import by.sample.entity.User;
import by.sample.entity.operationType.OperationType;
import by.sample.exception.BadRequestException;
import by.sample.model.OperationModel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CalculatorService {
    private HistoryDAO historyDAO;
    private OperationList operationList;

    public CalculatorService(HistoryDAO historyDAO, OperationList operationList) {
        this.historyDAO = historyDAO;
        this.operationList = operationList;
    }

    public Operation execute(OperationModel operation, User user) {

        OperationType operationType = null;
        for(OperationType item : operationList.get()) {
            if(item.getName().equals(operation.getOperation())) {
                operationType = item;
                break;
            }
        }

        Operation newOperation = new Operation(operationType, Arrays.asList(operation.getNum1(), operation.getNum2()), user);
        historyDAO.save(newOperation);
        return newOperation;
    }

    public List<Operation> getHistory(User user) {
        return historyDAO.getAll(user);
    }
}
