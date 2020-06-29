package framework.route;

public class ResolvedRoute {

    private String controller;
    private String method;


    public String getController() {
        return controller;
    }

    public ResolvedRoute setController(String controller) {
        this.controller = controller;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public ResolvedRoute setMethod(String method) {
        this.method = method;
        return this;
    }
}
