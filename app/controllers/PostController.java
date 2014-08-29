package controllers;

import models.*;
import dao.*;
import factories.*;
import views.html.*;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import play.mvc.*;
import play.i18n.Messages;
import play.Logger;
import play.data.Form;

public class PostController extends Controller {

    final static Logger.ALogger logger = Logger.of(PostController.class);
    
    private final static UserDAO userDAO = (UserDAO) new UserDAOFactory().create();
    private final static PostDAO postDAO = (PostDAO) new PostDAOFactory().create();
    
    /* Filter featured posts from list of posts */
    public static List<Post> filterFeatured(List<Post> posts) {
        List<Post> featured = new ArrayList<Post>();
        for (Post p : posts) if (p.isFeatured && p.isPublished) featured.add(p);
        return featured;
    }        
        
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
        Post p = null;
        try {
            p = postDAO.getById(postId); 
        } catch (NullPointerException e) {
            logger.error("A post with ID " + postId + " does not exist!");
            return badRequest();
        }
        
        /* Backend check if user is allowed to delete */
        User loggedUser = userDAO.getByBlogname(session("user"));
        if (loggedUser.equals(p.user)) {
            logger.info("User " + loggedUser.blogname 
                            + "is allowed to delete post with id :: " 
                            + p.id);
            postDAO.markAsArchived(p);
            return Application.home();
        }
        
        logger.error("User " + loggedUser.blogname 
                            + "is NOT allowed to delete post with id :: " 
                            + p.id);
                            
        return Application.noAllow();
        
    }
    
}
