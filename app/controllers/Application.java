package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result about() {
        return ok(about.render());
    }

    public static Result contact() {
        return ok(contact.render());
    }

    public static Result mercury() {
        return ok(mercury.render());
    }

    public static Result venus() {
        return ok(venus.render());
    }

    public static Result earth() {
        return ok(earth.render());
    }

    public static Result mars() {
        return ok(mars.render());
    }

    public static Result jupiter() {
        return ok(jupiter.render());
    }

    public static Result saturn() {
        return ok(saturn.render());
    }

    public static Result neptune() {
        return ok(neptune.render());
    }

    public static Result uranus() {
        return ok(uranus.render());
    }



}
