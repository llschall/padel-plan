package org.llschall.padel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeautifierTest {

    // Test method to check the beautification of week names
    @Test
    void testBeautifyWeekName() {
        Beautifier beautifier = new Beautifier();

        String result = beautifier.beautifyWeekName("week-1.txt");
        Assertions.assertEquals("Week 1", result);
    }
}