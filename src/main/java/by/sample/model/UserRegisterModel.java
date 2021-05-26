package by.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterModel {

    @NotNull(message = "Имя обязателено для заполнения")
    @NotBlank(message = "Имя введено не корректно")
    private String name;

    @NotNull(message = "Логин обязателен для заполнения")
    @NotBlank(message = "Логин введен не корректно")
    private String login;

    @NotNull(message = "Пароль обязателен для заполнения")
    @NotBlank(message = "Пароль введен не корректно")
    private String password;

}
