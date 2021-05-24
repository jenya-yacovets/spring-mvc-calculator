package by.sample.model;

import by.sample.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterModel {
    private String name;
    private String login;
    private String password;

    public boolean validation() throws BadRequestException {
        if(name.trim().isEmpty()) throw new BadRequestException("Invalid name");
        if(login.trim().isEmpty()) throw new BadRequestException("Invalid login");
        if(password.isEmpty()) throw new BadRequestException("Invalid password");
        return true;
    }
}
