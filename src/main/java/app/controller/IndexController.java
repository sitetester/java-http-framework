package app.controller;

import framework.request.Request;
import framework.response.Response;
import framework.route.Route;

@Route(path = "/")
public class IndexController {

    public Response index(Request request) {
        return new Response("It works!");
    }
}
