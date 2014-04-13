package controllers;

import java.util.List;

import play.*;
import play.data.Form;
import play.mvc.*;

import models.Comment;
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

    public static Result saveComment() {
        if(request().method() == "POST") {
            // Read form data
            Form<Comment> form = Form.form(Comment.class).bindFromRequest();

            String page = form.field("page").value();
            String previousURL = "/planets/" + page.toLowerCase();

            // If errors, return the form
            if(form.hasErrors()) {
                System.out.println(form.errors());
                flash("danger", "There was an error with your request.");
                return redirect(previousURL);
            } else {
                String text = form.field("text").value();

                Comment comment = form.get();
                comment.save();

                flash("success", "Comment saved successfully.");
                return redirect(previousURL);
            }
        } else {
            flash("danger", "Invalid HTTP method.");
            return redirect(routes.Application.index());
        }
    }

    public static Result mercury() {
        List<Comment> comments = Comment.findByPage("Mercury");
        return ok(mercury.render(comments));
    }

    public static Result venus() {
        List<Comment> comments = Comment.findByPage("Venus");
        return ok(venus.render(comments));
    }

    public static Result earth() {
        List<Comment> comments = Comment.findByPage("Earth");
        return ok(earth.render(comments));
    }

    public static Result mars() {
        List<Comment> comments = Comment.findByPage("Mars");
        return ok(mars.render(comments));
    }

    public static Result jupiter() {
        List<Comment> comments = Comment.findByPage("Jupiter");
        return ok(jupiter.render(comments));
    }

    public static Result saturn() {
        List<Comment> comments = Comment.findByPage("Saturn");
        return ok(saturn.render(comments));
    }

    public static Result neptune() {
        List<Comment> comments = Comment.findByPage("Neptune");
        return ok(neptune.render(comments));
    }

    public static Result uranus() {
        List<Comment> comments = Comment.findByPage("Uranus");
        return ok(uranus.render(comments));
    }

}
