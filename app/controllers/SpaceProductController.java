package controllers;

import models.SpaceProduct;
import play.*;
import play.mvc.*;
import play.data.Form;
import actions.*;
import models.*;

import views.html.*;

import java.util.List;
import java.util.logging.Logger;

public class SpaceProductController extends Controller {

    //@Security.Authenticated(Secured.class)
    public static Result list() {
        List<SpaceProduct> spaceProducts = SpaceProduct.find.all();
        return ok(views.html.spaceProduct.list.render(spaceProducts));
    }

    /**
     * Search SpaceProducts
     */
    public static Result search(String queryString) {
        List<SpaceProduct> spaceProducts = null;
        if(queryString!=null && queryString.length()>0) {
            spaceProducts = SpaceProduct.find.where()
                    .ilike("name", "%" + queryString +"%")
                    .findList();
        }
        else
            spaceProducts = SpaceProduct.find.all();
        return ok(views.html.spaceProduct.list.render(spaceProducts));
    }

    // Create a new form with id=0 when a request for new product is generated
    // Creates a filled in form with existing product info, when edit request is issued.

    @Auth({User.Role.Admin, User.Role.Customer})
    public static Result create(Long id) {
        Form<SpaceProduct> form = null;
        List<Manufacturer> manufacturers = Manufacturer.find.all();
        if(id == 0)
            form = Form.form(SpaceProduct.class).fill(new SpaceProduct());
        else
            form = Form.form(SpaceProduct.class).fill(SpaceProduct.findById(id.intValue()));
        return ok(views.html.spaceProduct.form.render(form, manufacturers));
    }

    // Saves the submitted form if id = 0 (new request)
    // Updates the submitted form if id != 0 (edit request)
    public static Result save() {
        List<Manufacturer> manufacturers = Manufacturer.find.all();
        if(request().method() == "POST")
        {
            // Read form data
            Form<SpaceProduct> form = Form.form(SpaceProduct.class).bindFromRequest();

            // Validate the name
            if(form.field("name").valueOr("").isEmpty()){
                Logger.info(form.field("name").valueOr(""));
                form.reject("name", "Space Product name cannot be empty");
            }
            // Validate the description
            if(form.field("description").valueOr("").isEmpty()){
                Logger.info(form.field("description").valueOr(""));
                form.reject("description", "Space Product description cannot be empty!!");
            }
            // If errors, return the form
            if(form.hasErrors()){
                Logger.info("Why am I here!!!");
                flash("danger", "Space Product form submission has error!!!");
                return badRequest(views.html.spaceProduct.form.render(form, manufacturers));
            }
            else{
                Manufacturer manufacturer = Manufacturer.findById(Integer.parseInt(form.field("manufacturer_id").value()));
                SpaceProduct p = form.get();
                p.manufacturer = manufacturer;
                if(p.id == 0)
                    p.save();
                else
                    p.update();
                flash("success", "Space Product created/updated successfully!!!");
                return redirect(routes.SpaceProductController.list());
            }
        }
        else{
            flash("danger", "Invalid Space Product edit request!!!");
            return redirect(routes.SpaceProductController.list());
        }
    }
}
