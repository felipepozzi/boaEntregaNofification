package com.boaEntregaNotification.service.webhook;

import java.io.IOException;

public interface WebhookService {
    void sendNotifcationWebhook(String body) throws IOException, InterruptedException;
}
