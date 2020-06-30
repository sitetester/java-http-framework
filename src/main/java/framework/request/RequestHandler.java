package framework.request;

import framework.response.Response;
import framework.route.ResolvedRoute;
import framework.route.RouteResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestHandler {

    public Response handleRequest(Request request) {

        ResolvedRoute resolvedRoute = new ResolvedRoute();

        try {
            resolvedRoute = new RouteResolver().ResolveRoute(request);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Response response = null;
        try {
            response = invokeControllerMethod(resolvedRoute);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return response;
    }

    private Response invokeControllerMethod(ResolvedRoute resolvedRoute)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String controller = resolvedRoute.getController();
        Class<?> controllerClass = Class.forName(controller);
        Object obj = controllerClass.newInstance();

        String method = resolvedRoute.getMethod();
        Method methodName = obj.getClass().getMethod(method);

        return (Response) methodName.invoke(obj);
    }
}
