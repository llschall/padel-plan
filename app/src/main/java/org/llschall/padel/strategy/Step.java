package org.llschall.padel.strategy;

import org.llschall.padel.Session;

import java.util.List;

abstract class Step {

    List<Session> process(List<Session> sessions) {
        return sessions;
    }

}
