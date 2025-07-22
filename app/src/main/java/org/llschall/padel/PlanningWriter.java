package org.llschall.padel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.llschall.padel.strategy.IStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanningWriter {

    void write(Planning sessions, IStrategy... strategies) throws IOException {

        for (IStrategy strategy : strategies) {
            // clone the sessions to avoid modifying the original list
            Planning clone = sessions.copy();
            strategy.process(clone);
        }

        System.out.println("Writing the planning...");

        // Create a new HTML document with a head and body
        Document document = new Document("");
        Element head = document.head();

        head.appendElement("title").text("Planning");
        head.appendElement("link")
                .attr("rel", "stylesheet")
                .attr("type", "text/css")
                .attr("href", "planning.css");

        Element body = document.body();

        Element root = body.appendElement("table")
                .attr("border", "1");

        Element row = root.appendElement("tr")
                .appendElement("td")
                .attr("colspan", Integer.toString(strategies.length));

        row.appendElement("h1").attr("style", "text-align:center;").text("Poll Ranking");
        String html = createHtml(sessions);
        row.append(html);

        row = root.appendElement("tr");

        insertArrows(row, strategies.length);

        row = root.appendElement("tr");

        for (IStrategy strategy : strategies) {

            Element cursor = row.appendElement("td");
            cursor.appendElement("h1").text(strategy.getName());
            cursor.append(strategy.getDetails());
        }

        row = root.appendElement("tr");

        insertArrows(row, strategies.length);

        row = root.appendElement("tr");

        for (IStrategy strategy : strategies) {

            Element cursor = row.appendElement("td");
            cursor.appendElement("h1").text("Planning");
            cursor.append(createHtml(strategy.getOptimized()));
        }

        row = root.appendElement("tr");

        for (IStrategy strategy : strategies) {

            Rater rater = new Rater(strategy.getOptimized());

            Element cursor = row.appendElement("td");
            cursor.appendElement("h2").text("Rating: " + rater.score + "%");
            Element table = cursor.appendElement("table");
            Element trHeader = table.appendElement("tr");
            List<Player> list = new ArrayList<>(rater.map.keySet())
                    .stream()
                    .sorted((a,b) -> (a.name.compareTo(b.name)))
                    .toList();
            int min = rater.map.values().stream().map(Rating::getRating).min(Integer::compareTo).get();

            for (Player player : list) {
                trHeader.appendElement("th").text(player.name);
            }
            Element trSlot = table.appendElement("tr");
            for (Player player : list) {
                Rating rating = rater.map.get(player);
                trSlot.appendElement("td").text(String.valueOf(rating.slot));
            }
            Element trSub = table.appendElement("tr");
            for (Player player : list) {
                Rating rating = rater.map.get(player);
                trSub.appendElement("td").addClass("substitute").text("(" + rating.substitute + ")");
            }
            Element trRating = table.appendElement("tr");
            for (Player player : list) {
                Rating rating = rater.map.get(player);
                boolean isMin = rating.getRating() == min;
                Element td = trRating.appendElement("td");
                if(isMin)  td = td.appendElement("b").appendElement("u");
                td.text(rating.getRating() + "%");
            }
        }

        Path path = Paths.get("files/out/planning.html");
        Files.write(path, document.html().getBytes());
    }

    void insertArrows(Element cursor, int count) {
        for (int i = 0; i < count; i++) {
            cursor.appendElement("td")
                    .appendElement("img")
                    .attr("src", "arrow.svg")
                    .attr("alt", "A")
                    .attr("style", "width:128px;height:32px;display:block;margin:auto;");
        }
    }

    public String createHtml(Planning weeks) {
        // Use jsoup to build the table
        Document doc = Jsoup.parse("<table style=margin:auto;></table>");
        Element table = doc.selectFirst("table");

        Element trHeader = table.appendElement("tr");
        for (Session week : weeks) {
            trHeader.appendElement("th").text(new Beautifier().beautifySessionName(week.name));
        }

        Element trBody = table.appendElement("tr");
        for (Session week : weeks) {
            Element td = trBody.appendElement("td");
            Element ul = td.appendElement("ul");
            for (Slot slot : week.slots) {
                Element li = ul.appendElement("li");
                if (slot.isSubstitute) {
                    li.addClass("substitute");
                    li.text("(" + slot.player.name + ")");
                } else {
                    li.text(slot.player.name);
                }
            }
        }
        return table.outerHtml();
    }

}
