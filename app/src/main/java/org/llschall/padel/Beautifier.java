package org.llschall.padel;

public class Beautifier {

    String beautifyWeekName(String weekName) {
        String name = weekName.replace("-", " ");
        name = name.replace(".txt", "");
        name = name.replace("week", "Week");
        return name;
    }

}
