package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends Model {

    @Id
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.Email
    public String email = "me@example.com";        // Used for user id

    @Constraints.Required
    @Formats.NonEmpty
    public String firstName;

    @Formats.NonEmpty
    @Constraints.Required
    public String lastName;

    @Constraints.Required
    @Constraints.MinLength(6)
    @Constraints.MaxLength(20)
    public String password;

    @Constraints.Required
    public Role role = Role.Customer;

    /* Default finder */
    public static Finder<String, User> find = new Finder<String, User>(
            String.class, User.class
    );

    public static List<User> findAll()
    {
        return find.all();
    }

    public static User findByEmail(String _email)
    {
        return find.where().eq("email", _email).findUnique();
    }

    public static User authenticate(String _email, String _password)
    {
        return find.where().eq("email", _email).eq("password", _password).findUnique();
    }

    // Role type enumeration
    public enum Role {
        Admin(1),
        Customer(2),
        Supplier(3);

        public final int id;

        Role(int id) {
            this.id = id;
        }

        String toString(int id)
        {
            switch(id)
            {
                case 1: return "Admin";
                case 2: return "Customer";
                case 3: return "Supplier";
                default:return "Customer";
            }
        }
    }
}
