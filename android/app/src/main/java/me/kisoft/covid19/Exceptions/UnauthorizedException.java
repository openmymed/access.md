package me.kisoft.covid19.Exceptions;


public class UnauthorizedException extends Exception {
    String message;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
