package framework.response;

import java.util.HashMap;

public class Response {

    private String contents;
    private int code = 200;

    private HashMap<String, String> headers = new HashMap<>();

    public Response(String contents) {
        this.contents = contents;
    }

    public Response(String contents, int code) {
        this.contents = contents;
        this.code = code;
    }

    public String getContents() {
        return contents;
    }

    public Response setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public Response setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
        return this;
    }
}
