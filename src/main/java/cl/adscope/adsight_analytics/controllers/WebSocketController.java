package cl.adscope.adsight_analytics.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import cl.adscope.adsight_analytics.entities.Resumen;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    @MessageMapping("/resumen")
    @SendTo("/topic/resumen")
    public Resumen handleResumen(Resumen resumen) {
        return resumen;
    }
} 