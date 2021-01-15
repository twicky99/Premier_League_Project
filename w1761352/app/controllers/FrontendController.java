package controllers;

import play.mvc.*;

import javax.inject.Inject;
import com.typesafe.config.Config;
import java.io.File;

/**
* Class that handle Angular UI app, act as a proxy that allow Play App to access all resources or file in Angular UI Project
*/
public class FrontendController extends Controller {
    private final Config config;

    @Inject
    public FrontendController(Config config){
      this.config = config;
    }

    /**
    * handle access of UI index page
    */
    public Result index() {
      File file = new File("ui/src/index.html");
      return ok(file, true);
    }

    /**
    * Handle all UI Project resources so Play App can access it from its port
    */
    public Result assetOrDefault(String resource){
      if(resource.startsWith(config.getString("apiPrefix"))){
        return notFound();
      }else{
        if (resource.contains(".")){
          File file = new File("ui/src/"+resource);
          return ok(file, true);
        }else{
          return index();
        }  
      }

    }


    public Result welcome() {
      return ok("");
    }
    // End of Class
}
