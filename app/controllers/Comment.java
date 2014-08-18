package controllers;

import models.*;
import dao.*;
import views.html.*;

import java.util.Map;

import play.mvc.*;
import play.i18n.Messages;
import play.Logger;
import play.data.Form;

public class Comment extends Controller {

    final static Logger.ALogger logger = Logger.of(Comment.class);
   
    /* ------ Submit ------ */
    public static Result submit() {

        logger.debug("new comment submitted");
        logger.debug("receive data from ajax http POST request");
        
        final Map<String, String[]> postValues 
                        = request().body().asFormUrlEncoded();
        String content = postValues.get("content")[0];
        Long postId = Long.parseLong(postValues.get("post")[0]);

        models.Comment comment = new models.Comment();
        comment.content = content;
        comment.user = new UserDAO().getByBlogname(session("user"));
        comment.post = new PostDAO().getById(postId);

        logger.debug("init persistence in Database");
        new CommentDAO().create(comment); 

        return ok();
    }

}
