package org.llschall.padel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SessionFileReader {

    Planning readSessions() throws IOException {

        // Create a list to hold the weeks
        List<Session> list = new ArrayList<>();

        Path path = Paths.get("files/in");

        // List all files in the directory
        Stream<Path> paths = Files.list(path).sorted();

        paths.forEach((p) -> {
            // Read each session from the file and add it to the list
            Session session = readSession(p);
            list.add(session);
        });

        return new Planning(list);
    }

    Session readSession(Path path) {
        // Create a new Week object
        Session session = new Session(path.getFileName().toString());

        // Here you would read the file and populate the session object
        // For now, we will just return an empty session
        System.out.println("Reading session from: " + path);

        // Read the file content
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new PadelPlanException(e);
        }

        for (String line : lines) {
            Player player = new Player(line);
            session.slots.add(new Slot(player, false));
        }

        return session;
    }
}
