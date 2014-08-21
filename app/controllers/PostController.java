package controllers;

import models.*;
import dao.*;
import views.html.*;

import java.util.Map;

import play.mvc.*;
import play.i18n.Messages;
import play.Logger;
import play.data.Form;

public class PostController extends Controller {

    final static Logger.ALogger logger = Logger.of(PostController.class);
    
    /* Create a new Post */
    public static Result create() {
        return NewPost.submit();
    }
    
    /* Update an existing Post */
    public static Result update() {
        return EditPost.submit();
    }
    
    /* Mark an existing Post as archived */
    public static Result delete() {
                       
        /* Get id of post to delete */
        final Map<String, String[]> postValues 
                        = request().body().asFormUrlEncoded();
        Long postId = Long.parseLong(postValues.get("id")[0]);
        
        /* Get Comment woth ID -- check existence */
        PostDAO dao = new PostDAO();
        Post p = null;
        try {
            p = dao.getById(postId); 
        } catch (NullPointerException e) {
            logger.error("A post with ID " + postId + " does not exist!");
            return badRequest();
        }
        
        /* Backend check if user is allowed to delete */
        User loggedUser = new UserDAO().getByBlogname(session("user"));
        if (loggedUser.equals(p.user)) {
            logger.info("User " + loggedUser.blogname 
                            + "is allowed to delete post with id :: " 
                            + p.id);
            dao.markAsArchived(p);
            return Application.home();
        }
        
        logger.error("User " + loggedUser.blogname 
                            + "is NOT allowed to delete post with id :: " 
                            + p.id);
                            
        return Application.noAllow();
        
    }
    
}
