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

        String cClassName = resolvedRoute.getController();
        Class<?> cClass = Class.forName(cClassName);
        Object obj = cClass.newInstance();

        String mName = resolvedRoute.getMethod();
        Method cMName = obj.getClass().getMethod(mName);

        return (Response) cMName.invoke(obj);
    }
}
