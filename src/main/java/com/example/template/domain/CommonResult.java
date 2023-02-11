package com.example.template.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CommonResult {
    private boolean success;
    private int code;
    private String message;
}
