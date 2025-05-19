package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
//    If a client sends a message to /app/sendMessage (notice the /app prefix from WebSocketConfig), it will be routed to the method with this annotation.
    @MessageMapping("/sendMessage")
//    This tells Spring to broadcast the return value of the method to all clients subscribed to /topic/messages.
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message){
        return message;
    }

    @GetMapping("chat")
    public String chat(){
        return "chat";
    }
}
