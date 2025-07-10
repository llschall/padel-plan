package org.llschall.padel.strategy;

import org.llschall.padel.Planning;

public interface IStrategy {
    String getName();

    String getDetails();

    void process(Planning sessions);

    Planning getOptimized();
}
