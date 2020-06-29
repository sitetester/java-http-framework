package framework.request;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RequestTest {

    @Test
    public void requestLine() {

        var requestLines = new ArrayList<String>();
        requestLines.add("GET / HTTP/1.1");

        requestLines.add("Host: localhost:8081");
        requestLines.add("User-Agent: curl/7.64.1");
        requestLines.add("Accept-Encoding: deflate, gzip;q=1.0, *;q=0.5");

        var request = new RequestParser().parseRequest(requestLines);

        assert request.getMethod().equals("GET");
        assert request.getUri().equals("/");
        assert request.getHttpVersion().equals("HTTP/1.1");

        assert request.getHeaders().size() == 3;
        assert request.getHeaders().containsKey("Host");
        assert request.getHeaders().containsKey("Accept-Encoding");
    }
}
