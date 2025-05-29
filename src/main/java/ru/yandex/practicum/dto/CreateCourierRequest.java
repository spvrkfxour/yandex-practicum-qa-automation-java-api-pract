package ru.yandex.practicum.dto;

import lombok.Data;


@Data
public class CreateCourierRequest {
    private String login;
    private String password;
    private String firstName;

}
