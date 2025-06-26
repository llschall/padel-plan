package org.llschall.padel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PlanningWriter {

    void write(List<Week> weeks) throws IOException {
        System.out.println("Writing the planning...");

        Path path = Paths.get("files/out/planning.html");
        String html = createHtml(weeks);

        Files.write(path, html.getBytes());
    }

    String createHtml(List<Week> weeks) {
        StringBuilder html = new StringBuilder();

        html.append("<html>\n");
        html.append("<head>\n");
        html.append("<title>Planning</title>\n");
        html.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"planning.css\">");
        html.append("</head>\n");

        html.append("<body>\n");
        html.append("<h1>Planning</h1>\n");

        html.append("<table>\n");

        html.append("<tr>");
        for (Week week : weeks) {
            html.append("<th>");
            html.append(new Beautifier().beautifyWeekName(week.name));
            html.append("</th>\n");
        }

        html.append("</tr>\n");

        html.append("<tr>");
        for (Week week : weeks) {
            html.append("<td><ul>");

            week.players.forEach(player -> {
                html.append("<li>");
                html.append(player.name);
                html.append("</li>");
            });
            html.append("</ul></td>\n");
        }

        html.append("</tr>");
        html.append("</table>");


        html.append("</body>\n");
        html.append("</html>");

        return html.toString();
    }


}
