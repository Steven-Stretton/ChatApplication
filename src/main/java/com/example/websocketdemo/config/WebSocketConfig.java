package com.example.websocketdemo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * The HTTP connection is only used for the initial handshake, after the client has made a request to the server,
 * the HTTP connection is then upgraded to a TCP/IP connection which is used by the websocket.
 *
 * The websocket is used for transferring the message, however, it does not relay information to the container or
 * framework to understand how to route or process the message, this is where STOMP comes into play...
 *
 * STOMP is used to define things such as - how to send a message to users who are subscribed to a topic
 *                                        - how to send a message to a particular user
 *
 *
 */
@Configuration //Indicates that the class can be used by the Spring IoC container as a source of bean definitions
@EnableWebSocketMessageBroker //Used to enable our WebSocket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //Register a websocket endpoint that the clients will use to connect to the websocket server.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //STOMP == Stream Text-Oriented Messaging Protocol
        //SockJS is used to enable fallback options for the browsers that do not support websocket
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
