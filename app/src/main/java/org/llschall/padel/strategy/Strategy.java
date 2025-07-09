package org.llschall.padel.strategy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.llschall.padel.Balancer;
import org.llschall.padel.Optimizer;
import org.llschall.padel.PlanningWriter;
import org.llschall.padel.Session;

import java.util.List;

public class Strategy {

    public String name;

    public String details;

    public List<Session> optimized;


    public Strategy(String name) {
        this.name = name;
    }

    public void process(List<Session> sessions) {

        Balancer balancer = new Balancer();
        List<Session> balanced = balancer.balance(sessions);

        Document document = new Document("");

        Element pCounts = document.appendElement("p").appendElement("i");
        pCounts.appendText("Counts:");
        balancer.map.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                .forEach(entry -> pCounts.appendText(entry.getKey() + ":" + entry.getValue() + "; "));

        document.append(new PlanningWriter().createHtml(balanced));

        details = document.html();

        Optimizer optimizer = new Optimizer();
        optimized = optimizer.optimize(balanced);

    }

}
