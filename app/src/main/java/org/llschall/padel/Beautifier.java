package org.llschall.padel;

public class Beautifier {

    String beautifySessionName(String weekName) {
        String name = weekName.replace("-", " ");
        name = name.replace(".txt", "");
        name = name.replace("session", "Session");
        return name;
    }

}
