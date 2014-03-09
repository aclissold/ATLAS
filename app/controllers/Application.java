package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Assignment 4"));
    }

    public static Result about() {
        return TODO;
    }

    public static Result contact() {
        return TODO;
    }

}
