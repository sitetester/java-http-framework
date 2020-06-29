package framework.request;

import java.util.HashMap;

public class Request {

    private String xyz;
    private final int num = 111;

    // methods
    private String GET = "GET111";
    private String POST = "POST";
    private String PUT = "PUT";
    private String DELETE = "DELETE";
    private String method;

    private HashMap<String, String> headers = new HashMap<>();

    // host
    private String remoteHost = "";
    private String remoteAddress = "";
    private String remoteUser = "";

    private String uri = "";
    private String route = "";
    private String httpVersion;


    public String getGET() {
        return GET;
    }

    public Request setGET(String GET) {
        this.GET = GET;
        return this;
    }

    public String getPOST() {
        return POST;
    }

    public Request setPOST(String POST) {
        this.POST = POST;
        return this;
    }

    public String getPUT() {
        return PUT;
    }

    public Request setPUT(String PUT) {
        this.PUT = PUT;
        return this;
    }

    public String getDELETE() {
        return DELETE;
    }

    public Request setDELETE(String DELETE) {
        this.DELETE = DELETE;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Request setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public Request setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
        return this;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public Request setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
        return this;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public Request setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public Request setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getRoute() {
        return route;
    }

    public Request setRoute(String route) {
        this.route = route;
        return this;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Request setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public Request setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
        return this;
    }
}
