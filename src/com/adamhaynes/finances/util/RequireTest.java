package com.adamhaynes.finances.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Haynesy
 * Date: 10/08/12
 * Time: 5:17 PM
 */
public class RequireTest {

    @Test
    public void that(){
        try {
            Require.that(false, "some message");
            fail("expected exception");
        } catch (RequireException e){
            assertEquals("some message", e.getMessage());
        }
    }
}
