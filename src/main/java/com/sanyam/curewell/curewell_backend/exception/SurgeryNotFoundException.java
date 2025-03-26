package com.sanyam.curewell.curewell_backend.exception;

public class SurgeryNotFoundException extends RuntimeException {
    public SurgeryNotFoundException(String message) {
        super(message);
    }
}
