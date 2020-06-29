package app.controller;

import framework.response.Response;
import framework.route.Route;

@Route(path = "/")
public class IndexController {

    public Response index() {
        return new Response("It works!");
    }
}
