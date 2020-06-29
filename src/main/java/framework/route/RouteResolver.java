package framework.route;

import framework.request.Request;

import java.io.File;
import java.util.Objects;

public class RouteResolver {

    // TODO: apply check in configuration
    private final String controllersPath = System.getProperty("user.dir") + "/src/main/java/app/controller";

    public ResolvedRoute ResolveRoute(Request request) throws ClassNotFoundException {

        var resolvedRoute = new ResolvedRoute();
        File folder = new File(controllersPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile()) {
                String controllerNameWithExt = listOfFiles[i].getName();
                String controllerName = controllerNameWithExt.substring(0, controllerNameWithExt.indexOf("."));

                String name = "app.controller." + controllerName;

                if (Class.forName(name).isAnnotationPresent(framework.route.Route.class)) {
                    Route annotation = Class.forName(name).getAnnotation(framework.route.Route.class);

                    if (annotation.path().equals(request.getUri())) {
                        // TODO: check for method
                        return resolvedRoute.setController(name).setMethod("index");
                    }
                }
            }
        }

        return resolvedRoute;
    }
}
