package base;

import chat.ChatSocket;

public interface ChatService {
    void sendMessage(String data);
    void add(ChatSocket Socket);
    void remove(ChatSocket Socket);
}