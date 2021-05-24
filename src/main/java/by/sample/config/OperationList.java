package by.sample.config;

import by.sample.entity.operationType.MinusOperation;
import by.sample.entity.operationType.OperationType;
import by.sample.entity.operationType.PlusOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OperationList {
    private List<OperationType> list;

    public OperationList(PlusOperation plusOperation, MinusOperation minusOperation) {
        list = Arrays.asList(plusOperation, minusOperation);
    }

    public List<OperationType> get() {
        return new ArrayList<>(list);
    }
}
