package org.llschall.padel.strategy;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.llschall.padel.Balancer;
import org.llschall.padel.Optimizer;
import org.llschall.padel.PlanningWriter;
import org.llschall.padel.Session;

import java.util.List;

public class Strategy implements IStrategy {

    private String details;

    private List<Session> optimized;
    
    @Override
    public String getName() {
        return "Balanced Strategy";
    }

    @Override
    public String getDetails() {
        return details;
    }

    public List<Session> getOptimized() {
        return optimized;
    }

    @Override
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
