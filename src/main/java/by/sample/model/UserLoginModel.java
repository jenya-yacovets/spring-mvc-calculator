package by.sample.model;

import by.sample.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginModel {
    private String login;
    private String password;

    public boolean validation() throws BadRequestException {
        if(login.trim().isEmpty()) throw new BadRequestException("Invalid login");
        if(password.isEmpty()) throw new BadRequestException("Invalid password");
        return true;
    }
}
