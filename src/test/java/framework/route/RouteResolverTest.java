package framework.route;

import app.controller.BlogsController;
import framework.request.Request;
import org.junit.jupiter.api.Test;

public class RouteResolverTest {

    @Test
    public void blogRoute() throws ClassNotFoundException {

        var request = new Request();
        request
                .setMethod("GET")
                .setUri("/blogs");

        var rr = new RouteResolver();
        assert rr.ResolveRoute(request) == BlogsController.class;
    }
}
