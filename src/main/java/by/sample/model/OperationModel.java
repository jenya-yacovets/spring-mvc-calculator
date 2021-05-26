package by.sample.model;

import by.sample.validation.OperationTypeConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationModel {

    @NotNull
    @Min(value = -100000, message = "Минимальное значение -100000")
    @Max(value = 100000, message = "Максимально значение 100000")
    private double num1;

    @NotNull
    @Min(value = -100000, message = "Минимальное значение -100000")
    @Max(value = 100000, message = "Максимально значение 100000")
    private double num2;

    @OperationTypeConstraint(message = "Выбрана не верная операция")
    @NotNull(message = "Выберите тип операции")
    @NotBlank(message = "Выберите тип операции")
    private String operation;
}
