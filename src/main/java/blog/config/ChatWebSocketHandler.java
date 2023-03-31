package blog.config;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final ConcurrentHashMap<String, WebSocketSession> client = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> nicknameMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String currentSessionId = (String) session.getAttributes().get("HTTP.SESSION.ID");
        String sessionName = nicknameMap.get(currentSessionId);
        String name;
        if (sessionName == null) {
            name = "익명" + new Random().nextInt(10000);
            log.info("name1 = {}", name);
            nicknameMap.put(currentSessionId, name);
            client.put(session.getId(), session);
            broadcast(name + "님이 입장하셨습니다.");
        } else {
            name = sessionName;
            log.info("name2 = {}", name);
            client.put(session.getId(), session);
        }
//        session.getAttributes().put("name", name);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        String name = (String) session.getAttributes().get("name");
//        broadcast(name+"님이 나가셨습니다.");
//        nicknameMap.remove(name);
//        client.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String name = (String) session.getAttributes().get("name");
        String chatMessage = message.getPayload();
        broadcast(name+": "+chatMessage);
    }

    private void broadcast(String message) throws Exception {
        TextMessage textMessage = new TextMessage(message);
        for (WebSocketSession session : client.values()) {
            log.info(String.valueOf(textMessage));
            session.sendMessage(textMessage);
        }
    }
}

