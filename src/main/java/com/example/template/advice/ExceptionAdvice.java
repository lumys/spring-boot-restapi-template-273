package com.example.template.advice;

import com.example.template.domain.CommonResult;
import com.example.template.exception.CustomException;
import com.example.template.exception.SuccessException;
import com.example.template.service.ResponseService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(value = {
        SuccessException.class,
    })
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult okException(HttpServletRequest request, CustomException e) {
        return responseService.getSuccessResult(e);
    }
}
