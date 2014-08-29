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

public class CommentController extends Controller {

    final static Logger.ALogger logger = Logger.of(CommentController.class);
    
    private static final UserDAO userDAO = (UserDAO) new UserDAOFactory().create();
    private static final PostDAO postDAO = (PostDAO) new PostDAOFactory().create();
    private static final CommentDAO commentDAO = (CommentDAO) new CommentDAOFactory().create();
   
    /* Require all Comments from a blog-post */
    public static List<Comment> require(Post post, String blogname) {
        
        List<Comment> comments = new ArrayList<Comment>();
        for (Comment c : commentDAO.getAllFromUser(
                                userDAO.getByBlogname(blogname))) {
            if (post.belongsToComment(c)) comments.add(c); 
        }    
        return comments;
    }
   
    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("new comment submitted");
        logger.debug("receive data from ajax http POST request");
        
        final Map<String, String[]> postValues 
                        = request().body().asFormUrlEncoded();
        String content = postValues.get("content")[0];
        Long postId = Long.parseLong(postValues.get("post")[0]);

        Comment comment = new Comment();
        comment.content = content;
        
        //check if commenter is a logged in user otherwise user is null        
        try {
            comment.user = userDAO.getByBlogname(session("user"));
        } catch (NullPointerException e) {
            logger.error("Not logged user adds comment");
        }
        
        comment.post = postDAO.getById(postId);

        logger.debug("init persistence in Database");
        commentDAO.create(comment); 

        return ok();
    }
    
    @Security.Authenticated(Secured.class)
    public static Result remove() {
    
        logger.debug("purge comment");
        
        final Map<String, String[]> postValues 
                        = request().body().asFormUrlEncoded();
        Long commentId = Long.parseLong(postValues.get("id")[0]);
        
        /* Get Comment woth ID -- check existence */
        Comment c = null;
        try {
            c = commentDAO.getById(commentId); 
        } catch (NullPointerException e) {
            logger.error("A comment with ID " + commentId + " does not exist!");
            return badRequest();
        }
        
        /* Backend check if user is allowed to delete this comment
         * --> prevent.malicious POST requests --> user could have
         * changed ID in Browser debugging 
         *
         * Is allowed to delete comment only if owner of blog-post or
         * autor of comment 
         */
        User loggedUser = userDAO.getByBlogname(session("user"));
        if (loggedUser.equals(c.user) || loggedUser.equals(c.post.user)) {
            logger.info("User " + loggedUser.blogname 
                            + "is allowed to delete comment with id :: " 
                            + c.id);
            commentDAO.markAsDeleted(c);
            return ok();
        }
        
        logger.error("User " + loggedUser.blogname 
                            + "is NOT allowed to delete comment with id :: " 
                            + c.id);
                            
        return Application.noAllow();
        
    }

}
