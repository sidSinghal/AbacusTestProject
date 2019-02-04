package com.abacus.test.exception;

public class PersonException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public PersonException() {
        super();
    }

    public PersonException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
