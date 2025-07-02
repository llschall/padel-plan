package org.llschall.padel;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PlanningWriter {

    void write(List<Session> weeks) throws IOException {
        System.out.println("Writing the planning...");

        StringWriter writer = new StringWriter();
        writer.append("<html>\n");
        writer.append("<head>\n");
        writer.append("<title>Planning</title>\n");
        writer.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"planning.css\">");
        writer.append("</head>\n");
        writer.append("<body>\n");

        String html = createHtml(weeks);

        writer.append("<h1>Poll ranking</h1>\n");
        writer.append(html);

        Balancer balancer = new Balancer();
        List<Session> balanced = balancer.balance(weeks);

        writer.append("<h1>Balanced ranking</h1>\n");
        writer.append("<p><i>Counts: ");
        balancer.map.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                .forEach(entry -> writer.append(entry.getKey()).append(":").append(String.valueOf(entry.getValue())).append("; "));
        writer.append("</i></p>");
        writer.append(createHtml(balanced));

        Optimizer optimizer = new Optimizer();
        List<Session> optimized = optimizer.optimize(balanced);

        writer.append("<h1>Planning</h1>\n");
        writer.append(createHtml(optimized));

        writer.append("</body>\n");
        writer.append("</html>");

        Path path = Paths.get("files/out/planning.html");
        Files.write(path, writer.toString().getBytes());

    }

    String createHtml(List<Session> weeks) {
        StringBuilder html = new StringBuilder();

        html.append("<table>\n");

        html.append("<tr>");
        for (Session week : weeks) {
            html.append("<th>");
            html.append(new Beautifier().beautifySessionName(week.name));
            html.append("</th>\n");
        }

        html.append("</tr>\n");

        html.append("<tr>");
        for (Session week : weeks) {
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
