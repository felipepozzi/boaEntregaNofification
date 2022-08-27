package com.boaEntregaNotification.service.eventsHistory;

import java.io.IOException;

public interface EventsHistory {
    void sendHistoryEvents(String body) throws IOException, InterruptedException;

    void saveIndicadores(String body) throws IOException, InterruptedException;
}
