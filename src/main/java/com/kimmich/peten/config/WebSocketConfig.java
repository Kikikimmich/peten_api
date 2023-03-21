package com.kimmich.peten.config;

import com.kimmich.peten.webSocket.DefaultWebSocketHandler;
import com.kimmich.peten.webSocket.WebSocket;
import com.kimmich.peten.webSocket.WebSocketImpl;
import com.kimmich.peten.webSocket.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

//    /**
//     * 	注入ServerEndpointExporter，
//     * 	这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
//     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }

    @Bean
    public DefaultWebSocketHandler defaultWebSocketHandler() {
        return new DefaultWebSocketHandler();
    }

    @Bean
    public WebSocket webSocket() {
        return new WebSocketImpl();
    }

    @Bean
    public WebSocketInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(defaultWebSocketHandler(), "ws/message")
                .addInterceptors(webSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
