package framework.request;

import framework.response.Response;
import framework.route.ResolvedRoute;
import framework.route.RouteResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class ClientHandler extends Thread {

    private final Socket socket;
    private BufferedReader bufferedReader;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        var request = new RequestParser().parseRequest(readLines());

        ResolvedRoute resolvedRoute = new ResolvedRoute();
        try {
            resolvedRoute = new RouteResolver().ResolveRoute(request);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Response response = null;
        try {
            response = invokeControllerMethod(resolvedRoute);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        assert response != null;
        try {
            sendResponse(response);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void sendResponse(Response response) throws IOException {

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        // TODO: change 'success' accordingly

        String format = String.format("HTTP/1.1 %s %s", response.getCode(), "Success");
        writer.println(format);

        for (Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
            writer.println(entry.getKey() + ": " + entry.getValue());
        }

        writer.println("");

        writer.println(response.getContents());
        bufferedReader.close();
    }

    private Response invokeControllerMethod(ResolvedRoute resolvedRoute)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String cClassName = resolvedRoute.getController();
        Class<?> cClass = Class.forName(cClassName);
        Object obj = cClass.newInstance();

        String mName = resolvedRoute.getMethod();
        Method cMName = obj.getClass().getMethod(mName);

        return (Response) cMName.invoke(obj);
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
}
