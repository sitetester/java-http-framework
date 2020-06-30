package framework.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestParser {

    public Request parseRequest(ArrayList<String> lines) {

        var request = new Request();

        parseRequestLine(lines.get(0), request);
        var headerLines = lines.subList(1, lines.size());
        parseHeaders(headerLines, request);

        return request;
    }

    private void parseRequestLine(String requestLine, Request request) {

        String[] parts = requestLine.split("\\s");
        if (parts.length != 3) {
            throw new IllegalArgumentException(String.format("Invalid request line %s", requestLine));
        }

        request
                .setMethod(parts[0])
                .setUri(parts[1])
                .setHttpVersion(parts[2])
        ;
    }

    private void parseHeaders(List<String> headerLines, Request request) {

        var headers = new HashMap<String, String>();

        headerLines.forEach(headerLine -> {
            String[] parts = headerLine.split(":");
            headers.put(parts[0].trim(), parts[1].trim());
        });

        request.setHeaders(headers);
    }
}
