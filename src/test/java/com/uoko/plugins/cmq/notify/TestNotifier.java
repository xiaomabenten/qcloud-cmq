package com.uoko.plugins.cmq.notify;

import com.uoko.plugins.cmq.event.CMQApplicationEvent;

import java.util.ArrayList;
import java.util.List;

public class TestNotifier implements Notifier {
    private List<CMQApplicationEvent> events = new ArrayList<CMQApplicationEvent>();

    @Override
    public void notify(CMQApplicationEvent event) {
        this.events.add(event);
    }

    public List<CMQApplicationEvent> getEvents() {
        return events;
    }
}