package org.llschall.padel.strategy;

import org.llschall.padel.Balancer;
import org.llschall.padel.Optimizer;
import org.llschall.padel.PlanningWriter;
import org.llschall.padel.Session;

import java.io.StringWriter;
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

        StringWriter writer = new StringWriter();
        writer.append("<p><i>");
        writer.append("Counts: ");

        balancer.map.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()))
                .forEach(entry -> writer.append(entry.getKey() + ":" + entry.getValue() + "; "));

        writer.append("</i></p>");

        writer.append(new PlanningWriter().createHtml(balanced));

        details = writer.toString();

        Optimizer optimizer = new Optimizer();
        optimized = optimizer.optimize(balanced);
    }

}
