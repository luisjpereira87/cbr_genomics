package com.example.demo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomException extends Exception {

    private Integer code;
    private String title;
    private String message;

    public CustomException(Integer code, String title, String message) {
        super();
        this.code = code;
        this.title = title;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.code + " : " + this.title + " : " + this.message;
    }

}
