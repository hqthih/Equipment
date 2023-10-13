package com.example.manageequipment.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class DataHandler extends TextWebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("Message: {}", message.getPayload());
    }

}
