package framework.request;

import framework.response.Response;
import framework.response.ResponseWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private final Socket socket;
    private BufferedReader bufferedReader;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        var request = new RequestParser().parseRequest(readLines());
        Response response = new RequestHandler().handleRequest(request);

        assert response != null;
        try {
            sendResponse(response);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private ArrayList<String> readLines() {

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        String line = "";

        try {
            assert bufferedReader != null;
            line = bufferedReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        var lines = new ArrayList<String>();
        while (!line.equals("")) {
            lines.add(line);

            try {
                line = bufferedReader.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return lines;
    }

    private void sendResponse(Response response) throws IOException {

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        new ResponseWriter(writer, response).writeResponse();
        bufferedReader.close();
    }
}
