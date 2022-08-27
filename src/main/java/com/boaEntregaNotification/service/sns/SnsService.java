package com.boaEntregaNotification.service.sns;

public interface SnsService {
    void sendSnsMessage(String topic, Object messagem, String assunto);
}
