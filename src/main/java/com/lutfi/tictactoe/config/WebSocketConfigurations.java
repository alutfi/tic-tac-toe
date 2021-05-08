package com.lutfi.tictactoe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigurations implements WebSocketMessageBrokerConfigurer {

    public void registerStompEndPoints(StompEndpointRegistry registry){
        registry.addEndpoint("/gameplay").withSockJS();
    }

    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
    }
}
