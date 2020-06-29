package framework.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        var request = new RequestParser().parseRequest(readLines());
        /*
        System.out.println(request.getMethod());
        System.out.println(request.getUri());
        System.out.println(request.getHttpVersion());
        */
    }

    private ArrayList<String> readLines() {

        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        String line = "";

        try {
            assert in != null;
            line = in.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        var lines = new ArrayList<String>();
        while (!line.equals("")) {
            lines.add(line);

            try {
                line = in.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return lines;
    }


}
