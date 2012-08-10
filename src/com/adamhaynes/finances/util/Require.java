package com.adamhaynes.finances.util;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 10/08/12
 * Time: 5:18 PM
 */
public class Require {
    public static void that(boolean expression, String message) {
        if(!expression)
            throw new RequireException(message);
    }
}
