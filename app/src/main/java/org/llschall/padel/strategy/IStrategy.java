package org.llschall.padel.strategy;

import org.llschall.padel.Session;

import java.util.List;

public interface IStrategy {
    String getName();

    String getDetails();

    void process(List<Session> sessions);

    List<Session> getOptimized();
}
