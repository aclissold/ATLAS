package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;


@Entity
public class SpaceProduct extends Model {

    @Id
    public Long id = 0L;

    @Constraints.Required
    public String name;
    @Constraints.Required
    @Column(columnDefinition = "TEXT")
    public String description;
    public String image_url;
    public float price;
    public int rating;


    /*
    @ManyToOne
    public Manufacturer manufacturer = new Manufacturer();
    */

    /* Default finder */
    public static Finder<Long, SpaceProduct> find = new Finder<Long, SpaceProduct>(
            Long.class, SpaceProduct.class
    );

    public static SpaceProduct findById(int _id)
    {
        return find.where().eq("id", _id).findUnique();
    }
}

