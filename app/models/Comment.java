package models;

import java.util.List;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String user;

    @Constraints.Required
    public String page;

    @Constraints.Required
    public String text;

    /* Default finder */
    public static Finder<Long,Comment> find = new Finder<Long,Comment>(
            Long.class, Comment.class
            );

    public static List<Comment> findByPage(String page) {
        return find.where().eq("page", page).findList();
    }

}
