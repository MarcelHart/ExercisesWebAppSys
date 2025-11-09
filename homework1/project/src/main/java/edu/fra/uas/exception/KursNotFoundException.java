package edu.fra.uas.exception;

public class KursNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public KursNotFoundException(String message) {
        super(message);
    }
    
    public KursNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public KursNotFoundException(int code) {
        super("Could not find kurs with code: " + code);
    }
}