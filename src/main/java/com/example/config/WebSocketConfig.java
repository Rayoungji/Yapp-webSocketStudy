package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*메소드 설명: 클라이언트가 웹 소켓 서버에 연결하는데 사용할 웹 소켓 엔드포인드 메소드
    * Stomp - 메세징 프로토콜 (메세징 처리에 대한 다양한 기능을 처리하기 위해 예를 들어 특정 사용자에게 메세지를 보내기 등)
    * withSockJs - 웹 소켓을 지원하지 않는 브라우저에 대한 대처*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /*메소드 설명: 한 클라이언트에서 다른 클라이언트로 메세지를 라우팅 할때 쓰이는 메소드*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*setApplicationDestinationPrefixes - @MessageMapping 어노테이션이 달린 컨트롤러 메소드로 라우팅
        * -> 즉 개발자가 정의한 컨트롤러로 매핑하는 역할*/
        registry.setApplicationDestinationPrefixes("/app");
        /*enableSimpleBroker - 클라이언트가 송신한 데이터를 다른 클라이언트들에게 수신하는 엔드 포인트*/
        registry.enableSimpleBroker("/topic");
    }
}
