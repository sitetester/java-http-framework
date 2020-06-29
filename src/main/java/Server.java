import framework.request.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    private static final int PORT = 8081;

    public static void main(String[] args) throws IOException {
        new Server().execute();
    }

    public void execute() throws IOException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(String.format("\nServer running at http://localhost:%d", PORT));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        while (true) {
            assert serverSocket != null;
            var socket = serverSocket.accept();

            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("\nNew client connected at: " + time.format(new Date()));

            // start in a new thread
            new ClientHandler(socket).start();
        }
    }
}
