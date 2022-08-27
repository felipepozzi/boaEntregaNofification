package com.boaEntregaNotification.listener;

import com.boaEntregaNotification.service.eventsHistory.EventsHistory;
import com.boaEntregaNotification.service.webhook.WebhookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class BoaEntregaSqsListener {

    @Autowired
    private WebhookService webhookService;

    @Autowired
    private EventsHistory eventsHistory;

    @SqsListener(value = "${queue.entregas}", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void listenToFirstQueue(@Headers Map<String, Object> headers, String message) throws IOException, InterruptedException {

        if (headers.get("NOTIFICATION_SUBJECT_HEADER").equals("entregas_status")) {
            webhookService.sendNotifcationWebhook(message);
            log.info("Notificacao de {} enviada para o webhook: {}", headers.getOrDefault("NOTIFICATION_SUBJECT_HEADER", "Subject N/A"), message);
        }
        if (headers.get("NOTIFICATION_SUBJECT_HEADER").equals("indicadores")) {
            eventsHistory.saveIndicadores(message);
            log.info("Salvando {} na base de dados: {}", headers.getOrDefault("NOTIFICATION_SUBJECT_HEADER", "Subject N/A"), message);
        }
        if (headers.get("NOTIFICATION_SUBJECT_HEADER").equals("faturamento")) {
            eventsHistory.sendHistoryEvents(message);
            log.info("Evento de {} salvo no banco", headers.getOrDefault("NOTIFICATION_SUBJECT_HEADER", "Subject N/A"));
        } else {
            log.info("Mensagem desprezada: {}", headers.getOrDefault("NOTIFICATION_SUBJECT_HEADER", "Subject N/A"));
        }
    }
}
