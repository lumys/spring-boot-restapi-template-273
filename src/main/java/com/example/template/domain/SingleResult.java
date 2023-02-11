package com.example.template.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}
