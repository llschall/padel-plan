package org.llschall.padel;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PlanningWriter {

    void write(List<Week> weeks) throws IOException {
        System.out.println("Writing the planning...");

        StringWriter writer = new StringWriter();
        writer.append("<html>\n");
        writer.append("<head>\n");
        writer.append("<title>Planning</title>\n");
        writer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"planning.css\">");
        writer.append("</head>\n");
        writer.append("<body>\n");

        String html = createHtml(weeks);

        Optimizer optimizer = new Optimizer();
        List<Week> optimized = optimizer.optimize(weeks);
        String html1 = createHtml(optimized);

        writer.append("<h1>Poll ranking</h1>\n");
        writer.append(html);

        writer.append("<h1>Planning</h1>\n");
        writer.append(html1);

        writer.append("</body>\n");
        writer.append("</html>");

        Path path = Paths.get("files/out/planning.html");
        Files.write(path, writer.toString().getBytes());

    }

    String createHtml(List<Week> weeks) {
        StringBuilder html = new StringBuilder();

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

            for (Slot slot : week.slots) {
                html.append("<li");
                if (slot.isSubstitute)
                    html.append(" class=\"substitute\"");
                html.append(">");
                if (slot.isSubstitute)
                    html.append("(");
                html.append(slot.player.name);
                if (slot.isSubstitute)
                    html.append(")");
                html.append("</li>");
            }
            html.append("</ul></td>\n");
        }

        html.append("</tr>");
        html.append("</table>");

        return html.toString();
    }


}
