package com.boaEntregaNotification.service.eventsHistory.webhook;

import java.io.IOException;

public interface EventsHistory {
    void sendHistoryEvents(String body) throws IOException, InterruptedException;
}
