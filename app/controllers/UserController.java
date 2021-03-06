package controllers;

import play.*;
import play.mvc.*;

import models.User;

import views.html.*;

import play.data.Form;

public class UserController extends Controller {

    public static class Login {
        public String username;
        public String password;

        public String validate() {
            if (User.authenticate(username, password) == null) {
                return "Invalid username or password";
            }
            return null;
        }
    }

    public static Result login() {
        return ok(views.html.auth.login.render());
    }

    public static Result authenticate() {
        Form<Login> form = Form.form(Login.class).bindFromRequest();

        if (form.hasErrors()) {
            System.out.println((form.errors()));
            flash("danger", "Authentication failed.");
            return redirect(routes.UserController.login());
        } else {
            User user = User.findByUsername(form.get().username);
            session().clear();
            session("username", form.get().username);
            flash("success", "Successfully logged in.");
            return redirect(routes.Application.index());
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "Successfully logged out.");
        return redirect(routes.Application.index());
    }

    public static Result save() {
        Form<User> form = Form.form(User.class).bindFromRequest();

        if (form.hasErrors()) {
            System.out.println(form.errors());
            flash("danger", "There was an error with your request.");
            return redirect(routes.UserController.login());
        }

        User user = form.get();
        if (User.findByUsername(user.username) == null) {
            user.save();
            flash("success", "Account created successfully!");
        } else {
            flash("danger", "Sorry, that username is already taken.");
        }
        return redirect(routes.UserController.login());
    }

}
