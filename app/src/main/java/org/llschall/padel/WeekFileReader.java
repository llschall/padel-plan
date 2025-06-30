package org.llschall.padel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WeekFileReader {

    List<Week> readWeeks() throws IOException {

        // Create a list to hold the weeks
        List<Week> list = new ArrayList<>();

        Path path = Paths.get("files/in");

        // List all files in the directory
        Stream<Path> paths = Files.list(path).sorted();

        paths.forEach((p) -> {
            // Read each week from the file and add it to the list
            Week week = readWeek(p);
            list.add(week);
        });

        return list;
    }

    Week readWeek(Path path) {
        // Create a new Week object
        Week week = new Week(path.getFileName().toString());

        // Here you would read the file and populate the week object
        // For now, we will just return an empty week
        System.out.println("Reading week from: " + path);

        // Read the file content
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new PadelPlanException(e);
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Player player = new Player(line);
            week.slots.add(new Slot(player, false));
        }

        return week;
    }
}
