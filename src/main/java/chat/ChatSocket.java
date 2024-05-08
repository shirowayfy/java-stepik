package chat;

import java.io.*;
import java.net.Socket;

public class ChatSocket extends Thread {

    private final Socket socket;
    private final ChatServiceImpl chatService;
    private final PrintWriter out;
    private final BufferedReader in;

    public ChatSocket(Socket socket, ChatServiceImpl chatService) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        this.chatService = chatService;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String inputString;
            while ((inputString = in.readLine()) != null) {
                String response = chatService.processMessage(inputString);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendString(String message) {
        out.println(message);
    }
}