package org.llschall.padel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Planning implements Iterable<Session> {

    final List<Session> sessions;

    public Planning(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public Iterator<Session> iterator() {
        return sessions.iterator();
    }

    public Planning copy() {

        ArrayList<Session> list = new ArrayList<>();
        for (Session session : sessions) {
            list.add(session.copy());
        }
        return new Planning(list);
    }
}
