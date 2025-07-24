package org.llschall.padel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Beautifier {

    String beautifySessionName(String date) {
        // Parse the date string into a LocalDate object
        LocalDate parsed = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE dd.MM");
        String name = parsed.format(formatter);
        // Capitalize first letter of day, rest lowercase
        name = name.substring(0, 1).toUpperCase() + name.substring(1, 3).toLowerCase() + name.substring(3);
        return name;
    }

}
