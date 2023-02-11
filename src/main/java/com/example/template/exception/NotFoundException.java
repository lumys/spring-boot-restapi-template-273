package com.example.template.exception;

public class NotFoundException extends CustomException {

    private final static String MESSAGE_CODE = "notFound";

    @Override
    protected String getMessageCode() {
        return MESSAGE_CODE;
    }
}
