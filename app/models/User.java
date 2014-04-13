package models;

import play.data.validation.Constraints.*;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model {

    @Id
    public Long id;

    @Required
    @MinLength(3)
    public String username;

    @Required
    @MinLength(6)
    public String password;

    public enum Role {
        Admin(0),
        User(1);

        public final int id;

        Role(int id) {
            this.id = id;
        }
    }

    /* Default finder */
    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
            );

    public static User authenticate(String username, String password) {
        return find.where()
            .eq("username", username)
            .eq("password", password)
            .findUnique();
    }

    public static User findByUsername(String username) {
        return find.where().eq("username", username).findUnique();
    }
}
