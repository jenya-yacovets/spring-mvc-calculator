package by.sample.entity;

import by.sample.entity.operationType.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    private long id;
    private OperationType type;
    private List<Double> numbers;
    private User user;

    public Operation(OperationType type, List<Double> numbers, User user) {
        this.type = type;
        this.numbers = numbers;
        this.user = user;
    }

    public double result() {
        return type.getResult(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
