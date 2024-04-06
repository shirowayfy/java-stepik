package base;

import chat.ChatWebSocket;

public interface ChatService {

    void sendMessage(String data);
    void add(ChatWebSocket webSocket);
    void remove(ChatWebSocket webSocket);
}
