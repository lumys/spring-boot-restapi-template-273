package com.example.template.exception;

public class SuccessException extends CustomException {

    private final static String MESSAGE_CODE = "success";

    @Override
    protected String getMessageCode() {
        return MESSAGE_CODE;
    }
}
