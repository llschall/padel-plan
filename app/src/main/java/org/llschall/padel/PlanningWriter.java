package org.llschall.padel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlanningWriter {

    void write(List<Session> weeks) throws IOException {
        System.out.println("Writing the planning...");

        Document document = Jsoup.parse("<html><head></head><body></body></html>", "", Parser.xmlParser());
        Element head = document.head();
        head.appendElement("title").text("Planning");
        head.appendElement("link").attr("rel", "stylesheet").attr("type", "text/css").attr("href", "planning.css");
        Element body = document.body();

        body.appendElement("h1").text("Poll ranking");
        String html = createHtml(weeks);
        body.append(html);

        Balancer balancer = new Balancer();
        List<Session> balanced = balancer.balance(weeks);

        body.appendElement("h1").text("Balanced ranking");
        Element pCounts = body.appendElement("p").appendElement("i");
        pCounts.appendText("Counts: ");
        balancer.map.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                .forEach(entry -> pCounts.appendText(entry.getKey() + ":" + entry.getValue() + "; "));
        body.append(createHtml(balanced));

        Optimizer optimizer = new Optimizer();
        List<Session> optimized = optimizer.optimize(balanced);

        body.appendElement("h1").text("Planning");
        body.append(createHtml(optimized));

        body.appendElement("br");

        Element table = body.appendElement("table");
        Element trHeader = table.appendElement("tr");
        Rater rater = new Rater(optimized);
        List<Player> list = new ArrayList<>(rater.map.keySet());
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
            trRating.appendElement("td").text(rating.getRating() + "%");
        }

        Path path = Paths.get("files/out/planning.html");
        Files.write(path, document.html().getBytes());

    }

    String createHtml(List<Session> weeks) {
        // Use jsoup to build the table
        Document doc = Jsoup.parse("<table></table>");
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
