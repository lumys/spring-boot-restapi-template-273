package com.example.template.exception;

public class FailException extends CustomException {

    private final static String MESSAGE_CODE = "fail";

    @Override
    protected String getMessageCode() {
        return MESSAGE_CODE;
    }
}
