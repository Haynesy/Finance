package com.adamhaynes.finances.util;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 10/08/12
 * Time: 5:58 PM
 */
public class UnreachableCodeException extends RuntimeException {
    public UnreachableCodeException(){
        super("supposedly unreachable code was executed");
    }
}
