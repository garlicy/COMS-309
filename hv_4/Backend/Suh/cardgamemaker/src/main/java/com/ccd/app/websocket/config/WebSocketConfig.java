package com.ccd.app.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

//@Configuration
//@EnableWebSocketMessageBroker
//public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker
//    }
//}
	@Configuration
	public class WebSocketConfig {
	    @Bean
	    public ServerEndpointExporter serverEndpointExporter(){
	        return new ServerEndpointExporter();
	    }
	}