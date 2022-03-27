package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsDTO {

    private String title;
    private String text;
    private Boolean isPublish;
}
