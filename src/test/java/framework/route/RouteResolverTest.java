package framework.route;

import framework.request.Request;
import org.junit.jupiter.api.Test;

public class RouteResolverTest {

    @Test
    public void blogRoute() throws ClassNotFoundException {

        var request = new Request();
        request
                .setMethod("GET")
                .setUri("/blogs");

        var routeResolver = new RouteResolver();
        assert routeResolver.ResolveRoute(request).equals("app.controller.BlogsController:index");
    }
}
