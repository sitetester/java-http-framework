package framework.route;

import framework.request.Request;
import org.junit.jupiter.api.Test;

public class RouteResolverTest {

    @Test
    public void blogRoute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        var request = new Request();
        request
                .setMethod("GET")
                .setUri("/blogs");

        var rr = new RouteResolver();

        // let's assume path is src/app/controllers
        rr.ResolveRoute(request);

    }
}
