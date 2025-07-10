package org.llschall.padel;

import java.util.Iterator;
import java.util.List;

public class Planning implements Iterable<Session> {

    List<Session> sessions;

    public Planning(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public Iterator<Session> iterator() {
        return sessions.iterator();
    }

    protected Planning copy() {
        return new Planning(sessions);
    }
}
