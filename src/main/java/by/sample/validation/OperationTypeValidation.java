package by.sample.validation;

import by.sample.config.OperationList;
import by.sample.entity.operationType.OperationType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OperationTypeValidation implements ConstraintValidator<OperationTypeConstraint, String>{

    private final OperationList operationList;

    public OperationTypeValidation(OperationList operationList) {
        this.operationList = operationList;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean res = false;

        for (OperationType item : operationList.get()) {
            if (item.getName().equals(s)){
                res = true;
                break;
            }
        }

        return res;
    }
}
