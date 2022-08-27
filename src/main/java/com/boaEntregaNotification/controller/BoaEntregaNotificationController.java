package com.boaEntregaNotification.controller;

import com.boaEntregaNotification.service.sns.SnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boaentreganotification")
public class BoaEntregaNotificationController {

    private final SnsService snsService;

    @PostMapping(path = "/events/{assunto}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessageToFirstQueue(@PathVariable String assunto, @RequestBody Object mensagem) {
        snsService.sendSnsMessage("boaEntregaTopic", mensagem, assunto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
