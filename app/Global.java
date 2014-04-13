import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("db/init-task-data.yml");
        // Check if the database is empty
        if (Comment.find.findRowCount() == 0) {
            Ebean.save(all.get("comments"));
        }
    }
}
