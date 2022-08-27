package com.boaEntregaNotification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SnsMessage {
    @JsonProperty(value = "assunto")
    private String assunto;

    @JsonProperty(value = "mensagem")
    private String mensagem;
}
