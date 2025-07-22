package org.llschall.padel.strategy;

import org.llschall.padel.Balancer;
import org.llschall.padel.Optimizer;
import org.llschall.padel.Planning;
import org.llschall.padel.PlanningWriter;

import java.io.StringWriter;
import java.util.Comparator;
import java.util.Map;

public class BalancedStrategy implements IStrategy {

    private String details;

    private Planning optimized;

    @Override
    public String getName() {
        return "Balanced Strategy";
    }

    @Override
    public String getDetails() {
        return details;
    }

    public Planning getOptimized() {
        return optimized;
    }

    @Override
    public void process(Planning sessions) {

        Balancer balancer = new Balancer();
        Planning balanced = balancer.balance(sessions);

        StringWriter writer = new StringWriter();
        writer.append("<b>Work in Progress</b>");
        writer.append("<p><i>");
        writer.append("Counts: ");

        balancer.map.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .forEach(entry ->
                        writer.append(entry.getKey())
                                .append(":")
                                .append(String.valueOf(entry.getValue()))
                                .append("; "));

        writer.append("</i></p>");

        writer.append(new PlanningWriter().createHtml(balanced));

        details = writer.toString();

        Optimizer optimizer = new Optimizer();
        optimized = optimizer.optimize(balanced);
    }

}
