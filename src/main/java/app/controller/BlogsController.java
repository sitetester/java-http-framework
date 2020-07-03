package app.controller;

import framework.AbstractController;
import framework.request.Request;
import framework.response.Response;
import framework.route.Route;

@Route(path = "/blogs")
public class BlogsController extends AbstractController {

    public Response index(Request request) {
        return new Response("blogs: INDEX page.");
    }

    @Route(path = "/list")
    public Response list(Request request) {
        return new Response("Listing page!");
    }


    @Route(path = "/{id[\\d+]}")
    public Response byIdInt(Request request) {
        return new Response(String.format("%d page!", 123));
    }


    @Route(path = "/{title}")
    public Response byTitle(Request request) {
        return new Response(String.format("%s page!", "lorem ipsum"));
    }
}

