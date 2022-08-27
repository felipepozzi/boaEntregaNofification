package com.boaEntregaNotification.service.sns;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnsServiceImpl implements SnsService {

    private final NotificationMessagingTemplate messagingTemplate;

    public void sendSnsMessage(String topic, Object messagem, String assunto) {
        log.info("Escrevendo a mensagem {} no topico {}", messagem, topic);

        messagingTemplate.sendNotification(topic, messagem, assunto);
    }
}
