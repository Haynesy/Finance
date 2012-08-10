package com.adamhaynes.finances.util;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 10/08/12
 * Time: 5:21 PM
 */
public class RequireException extends RuntimeException {
    private String message;

    public RequireException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
