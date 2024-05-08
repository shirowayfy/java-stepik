package chat;

import base.ChatService;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServiceImpl implements ChatService{
    private Set<ChatSocket> Sockets;

    public ChatServiceImpl() {
        this.Sockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatSocket user : Sockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String processMessage(String message) {
        return "Processed: " + message;
    }

    public void add(ChatSocket Socket) {
        Sockets.add(Socket);
    }

    public void remove(ChatSocket Socket) {
        Sockets.remove(Socket);
    }

}