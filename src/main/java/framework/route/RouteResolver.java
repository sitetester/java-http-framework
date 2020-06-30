package framework.route;

import framework.request.Request;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Objects;

public class RouteResolver {

    // TODO: apply check in configuration
    private final String controllersPath = System.getProperty("user.dir") + "/src/main/java/app/controller";

    public ResolvedRoute ResolveRoute(Request request) throws ClassNotFoundException {

        var resolvedRoute = new ResolvedRoute();

        File folder = new File(controllersPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            File file = listOfFiles[i];

            if (file.isFile()) {
                String controllerFQN = getControllerFQN(file);

                if (Class.forName(controllerFQN).isAnnotationPresent(framework.route.Route.class)) {
                    Route annotation = Class.forName(controllerFQN).getAnnotation(framework.route.Route.class);

                    // if URI matches controller level path
                    if (annotation.path().equals(request.getUri())) {
                        resolvedRoute.setController(controllerFQN).setMethod("index");
                        break;
                    } else {
                        // check method level routes
                        var uriParts = request.getUri().split("/");

                        if (annotation.path().substring(1).equals(uriParts[1])) {
                            String methodName = this.checkMethodRoute(controllerFQN, uriParts);
                            if (methodName.equals("")) {
                                throw new IllegalArgumentException("No method level route found for URI" + request.getUri());
                            }

                            resolvedRoute.setController(controllerFQN).setMethod(methodName);
                        }
                    }
                }
            }
        }

        return resolvedRoute;
    }

    private String checkMethodRoute(String controllerFQN, String[] uriParts) throws ClassNotFoundException {

        for (Method method : Class.forName(controllerFQN).getDeclaredMethods()) {
            if (method.isAnnotationPresent(framework.route.Route.class)) {
                Route methodAnnotation = method.getAnnotation(framework.route.Route.class);
                if (methodAnnotation.path().substring(1).equals(uriParts[2])) {
                    return uriParts[2];
                }
            }
        }

        return "";
    }

    private String getControllerFQN(File file) {

        String controllerNameWithExt = file.getName();
        String controllerName = controllerNameWithExt.substring(0, controllerNameWithExt.indexOf("."));

        return "app.controller." + controllerName;
    }
}
