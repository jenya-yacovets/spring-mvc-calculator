package by.sample.model;

import by.sample.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationModel {
    private double num1;
    private double num2;
    private String operation;

    public boolean validation() throws BadRequestException {
        if(num1 == 0.0d) throw new BadRequestException("Invalid num1");
        if(num2 == 0.0d) throw new BadRequestException("Invalid num2");
        if(operation.isEmpty()) throw new BadRequestException("Invalid operation");
        return true;
    }
}
