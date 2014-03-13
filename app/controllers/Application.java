package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Assignment 4"));
    }

    public static Result about() {
        return ok(about.render());
    }

    public static Result contact() {
        return ok(contact.render());
    }

    public static Result saturn() {
        return ok(saturn.render());
    }

    public static Result jupiter() {
        return ok(jupiter.render());
    }

    public static Result mercury() {
        return ok(mercury.render());
    }

}
