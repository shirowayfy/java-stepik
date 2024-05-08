package servlets;

import chat.ChatServiceImpl;
import chat.ChatSocket;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Socket;
import java.net.ServerSocket;

@WebServlet(name = "SocketChatServlet", urlPatterns = {"/chat"})
public class SocketChatServlet extends HttpServlet {

    private final static int PORT = 5060;
    private final ChatServiceImpl chatService;

    public SocketChatServlet() {
        this.chatService = new ChatServiceImpl();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        new Thread(new ChatServer()).start();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Optional: handle GET requests
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Optional: handle POST requests
    }

    class ChatServer implements Runnable {
        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Chat Server started on port " + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket);
                    ChatSocket chatSocket = new ChatSocket(clientSocket, chatService);
                    chatSocket.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}