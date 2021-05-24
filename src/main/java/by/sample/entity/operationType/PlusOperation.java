package by.sample.entity.operationType;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlusOperation implements OperationType{
    private final String name = "Plus";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getResult(List<Double> listNumber) {
        return listNumber.get(0) + listNumber.get(1);
    }
}
