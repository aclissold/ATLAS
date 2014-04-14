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
