package org.llschall.padel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeautifierTest {

    // Test method to check the beautification of week names
    @Test
    void testBeautifySessionName() {
        Beautifier beautifier = new Beautifier();

        String result = beautifier.beautifySessionName("01-07-2025");
        Assertions.assertEquals("Tue 01.07", result);
    }
}