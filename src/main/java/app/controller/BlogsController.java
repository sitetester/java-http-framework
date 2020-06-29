package app.controller;

import framework.AbstractController;
import framework.response.Response;
import framework.route.Route;

@Route(path = "/blogs")
public class BlogsController extends AbstractController {

    public Response index() {
        return new Response("It works!");
    }
}

