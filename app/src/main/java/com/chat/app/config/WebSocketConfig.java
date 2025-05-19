package com.chat.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements  WebSocketMessageBrokerConfigurer {
    // This method registers the endpoint that clients will use to connect to the WebSocket server
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // Clients will connect to this endpoint (e.g., ws://localhost:8080/chat)
                .setAllowedOrigins("http://localhost:8080") // Allows cross-origin requests from this frontend (e.g., a React app running on Vite)
                .withSockJS(); // Enables fallback options (SockJS) if WebSockets are not supported by the browser
    }

    // This method configures the message broker (which handles routing of messages)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enables a simple in-memory message broker and defines the prefix used to filter destinations for outbound messages
        // e.g., messages sent to /topic/someRoom will be broadcast to subscribers
        registry.enableSimpleBroker("/topic");

        // Prefix for messages that are bound for methods annotated with @MessageMapping
        // e.g., if client sends to /app/message, it will be routed to a method with @MessageMapping("/message")
        registry.setApplicationDestinationPrefixes("/app");
    }
}
