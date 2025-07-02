package org.llschall.padel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeautifierTest {

    // Test method to check the beautification of week names
    @Test
    void testBeautifySessionName() {
        Beautifier beautifier = new Beautifier();

        String result = beautifier.beautifySessionName("session-1982.txt");
        Assertions.assertEquals("Session 1982", result);
    }
}