package com.example.template.exception;

public abstract class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final static String CODE = "code";
    private final static String MSG = "msg";
    private final static String SEPARATOR = ".";

    protected Object[] args;

    public Object[] getArgs() {
        return args;
    }

    public String getCode() {
        return String.join(SEPARATOR, getMessageCode(), CODE);
    }

    public String getMsg() {
        return String.join(SEPARATOR, getMessageCode(), MSG);
    }

    protected abstract String getMessageCode();
}
