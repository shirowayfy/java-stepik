package servlets;

import javax.servlet.annotation.WebServlet;

import chat.ChatServiceImpl;
import chat.ChatWebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebServlet(name = "WebSocketServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {

    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatServiceImpl chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatServiceImpl();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
