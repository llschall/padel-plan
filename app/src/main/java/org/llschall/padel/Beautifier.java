package org.llschall.padel;

public class Beautifier {

    String beautifySessionName(String weekName) {
        String name = weekName.replace("-", ".");
        name = name.replace("2025.", "");
        return name;
    }

}
