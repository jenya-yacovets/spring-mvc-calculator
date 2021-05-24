package by.sample.entity.operationType;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinusOperation implements OperationType{
    @Override
    public String getName() {
        return "Minus";
    }

    @Override
    public double getResult(List<Double> listNumber) {
        return listNumber.get(0) - listNumber.get(1);
    }
}
