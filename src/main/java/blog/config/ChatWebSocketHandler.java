package blog.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> client = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = "익명" + new Random().nextInt(10000);
        session.getAttributes().put("name", name);
        client.put(session.getId(), session);
        broadcast(name + "님이 입장하셨습니다.");
        log.info("name = {}", name);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String name = (String) session.getAttributes().get("name");
        if (name != null) {
            broadcast(name+"님이 나가셨습니다.");
            client.remove(session.getId());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String name = (String) session.getAttributes().get("name");
        log.info("name = {}", name);
        String chatMessage = message.getPayload();
        broadcast(name+": "+chatMessage);
    }

    private void broadcast(String message) throws Exception {
        TextMessage textMessage = new TextMessage(message);
        for (WebSocketSession session : client.values()) {
            if (session.isOpen()) {
                log.info(String.valueOf(textMessage));
                session.sendMessage(textMessage);
            }
        }
    }

}

