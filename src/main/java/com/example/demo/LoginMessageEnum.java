package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginMessageEnum {
    notMatch(-2, "password does not matched"),
    notExist(-1, "does not exist user");

    int status;
    String message;
}
