package com.kimmich.peten.webSocket.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import com.kimmich.peten.webSocket.constant.MessageTypeConst;
import com.kimmich.peten.webSocket.dto.CommonMessageDTO;
import com.kimmich.peten.webSocket.dto.CommonReplyDTO;
import com.kimmich.peten.webSocket.dto.MessageTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class WebSocketImpl implements WebSocket {
    /**
     * 在线连接数（线程安全）
     */
    private final AtomicInteger connectionCount = new AtomicInteger(0);

    /**
     * 线程安全的无序集合（存储会话）
     */
    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void handleOpen(WebSocketSession session) {
        sessions.add(session);
        int count = connectionCount.incrementAndGet();
        log.info("a new connection opened，current online count：{}", count);
    }

    @Override
    public void handleClose(WebSocketSession session) {
        sessions.remove(session);
        int count = connectionCount.decrementAndGet();
        log.info("a new connection closed，current online count：{}", count);
    }

    @Override
    public void handleMessage(WebSocketSession session, String message) {
        // 只处理前端传来的文本消息，并且直接丢弃了客户端传来的消息
        log.info("received a message：{}", message);

        if ("ping".equals(message)){
            return;
        }

        MessageTypeDTO messageTypeDTO = null;
        try {
            messageTypeDTO = JSONObject.parseObject(message).toJavaObject(MessageTypeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (messageTypeDTO == null) {
            try {
                this.sendMessage(session, "不支持的消息类型：" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        assert messageTypeDTO.getType() != null;

        switch (messageTypeDTO.getType()) {
            case MessageTypeConst.TYPE_USER:
                doUserMassage(session, messageTypeDTO.getMessage());
                break;
            case MessageTypeConst.TYPE_SHOP:
                doShopMassage(session, messageTypeDTO.getMessage());
            case MessageTypeConst.TYPE_HOSPITAL:
                doHospitalMassage(session, messageTypeDTO.getMessage());
            case MessageTypeConst.TYPE_SYSTEM:
                doSystemMassage(session, messageTypeDTO.getMessage());
            default:
                doDefaultMessage(session, messageTypeDTO.getMessage());
        }


        // 回复一个
//        try{
//            this.sendMessage(session, "回复+" + message);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void sendMessage(WebSocketSession session, String message) throws IOException {
        this.sendMessage(session, new TextMessage(message));
    }

    @Override
    public void sendMessage(String userId, TextMessage message) throws IOException {
        Optional<WebSocketSession> userSession = sessions.stream().filter(session -> {
            if (!session.isOpen()) {
                return false;
            }
            Map<String, Object> attributes = session.getAttributes();
            if (!attributes.containsKey("uid")) {
                return false;
            }
            String uid = (String) attributes.get("uid");
            return uid.equals(userId);
        }).findFirst();
        if (userSession.isPresent()) {
            userSession.get().sendMessage(message);
        }
    }

    @Override
    public void sendMessage(String userId, String message) throws IOException {
        this.sendMessage(userId, new TextMessage(message));
    }

    @Override
    public void sendMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(message);
    }

    @Override
    public void broadCast(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (!session.isOpen()) {
                continue;
            }
            this.sendMessage(session, message);
        }
    }

    @Override
    public void broadCast(TextMessage message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (!session.isOpen()) {
                continue;
            }
            session.sendMessage(message);
        }
    }

    @Override
    public void handleError(WebSocketSession session, Throwable error) {
        log.error("websocket error：{}，session id：{}", error.getMessage(), session.getId());
        log.error("", error);
    }

    @Override
    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public int getConnectionCount() {
        return connectionCount.get();
    }


    private void doUserMassage(WebSocketSession session, CommonMessageDTO message) {
    }

    private void doShopMassage(WebSocketSession session, CommonMessageDTO message) {

        if (message == null){
            try {
                this.sendMessage(session, "不支持的消息类型：" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // 正式业务
        // 还没连接上客服
        if (StrUtil.isBlank(message.getTargetId())){

            // 配置客服

        }

        // 先假装回复一下 mock
        try {
            this.sendMessage(session, CommonReplyDTO.succeed(CommonMessageDTO.builder()
                    .message("请稍等...")
                    .build()));
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void doHospitalMassage(WebSocketSession session, CommonMessageDTO message) {
    }

    private void doSystemMassage(WebSocketSession session, CommonMessageDTO message) {
    }

    private void doDefaultMessage(WebSocketSession session, CommonMessageDTO message) {
    }
}

