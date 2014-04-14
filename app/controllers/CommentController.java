package controllers;

import play.*;
import play.mvc.*;

import models.Comment;

import views.html.*;

import play.data.Form;

public class CommentController extends Controller {

    public static Result save() {
        if(request().method() == "POST") {
            // Read form data
            Form<Comment> form = Form.form(Comment.class).bindFromRequest();

            // If errors, return the form
            if(form.hasErrors()) {
                System.out.println(form.errors());
                flash("danger", "There was an error with your request.");
                return redirect(request().getHeader("referer"));
            } else {
                String text = form.field("text").value();

                Comment comment = form.get();
                comment.save();

                flash("success", "Comment saved successfully.");
                return redirect("/planets/" + comment.page.toLowerCase());
            }
        } else {
            flash("danger", "Invalid HTTP method.");
            return redirect(routes.Application.index());
        }
    }

    public static Result edit(Long id) {
        Comment comment = Comment.find.byId(id);
        if (comment == null) {
            flash("danger", "Specified comment does not exist.");
            return redirect(request().getHeader("referer"));
        }
        return ok(views.html.editComment.render(comment));
    }

    public static Result delete(Long id) {
        Comment comment = Comment.find.byId(id);
        if (comment == null) {
            flash("danger", "Error: attempted to delete nonexistent comment.");
        } else {
            flash("success", "Comment deleted successfully.");
            comment.delete();
        }
        return redirect(request().getHeader("referer"));
    }

}
