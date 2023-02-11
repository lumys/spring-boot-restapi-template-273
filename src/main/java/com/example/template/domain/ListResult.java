package com.example.template.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private int total;
    private List<T> list;
}
