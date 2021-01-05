package com.example.core.exception;

public class TestFrameworkException extends RuntimeException{

    public TestFrameworkException(Exception inner) {
        super(inner);
    }

    public TestFrameworkException(String message, Exception inner) {
        super(message, inner);
    }

    public TestFrameworkException(String message) {
        super(message);
    }
}
