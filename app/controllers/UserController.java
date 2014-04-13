package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class UserController extends Controller {

    public static Result login() {
        return ok(views.html.auth.login.render());
    }

}
