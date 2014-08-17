package controllers;

import play.mvc.*;
import models.Picture;
import dao.*;

import play.Logger;

import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.*;

import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.io.FileUtils;

import play.libs.Json;

public class PictureHandler extends Controller {

    final static Logger.ALogger logger = Logger.of(PictureHandler.class);

    /* Path for uploaded Pictures to be stored on Filesystem 
        and loaded on web respectively */
    private static final String PICTURE_FS_PATH = 
        ConfigFactory.load().getString("picturesfspath");
    
    /* ------ Load Picture from Filesystem ------*/
    /* We need this method because we cannot access the Filesystem directly
       from the views (External Assets are not allowed in production mode
       for security reasons) This method is called in the view */
    public static Result loadPicture(String filename) {    
        logger.debug("loading image :: " + filename + "...");

        /* Get File */
        File imageFile = new File(PICTURE_FS_PATH, filename);
        String contentType = "";
    
        try {        
            /* Get File Content Type*/
            contentType = Files.probeContentType(imageFile.toPath());
            logger.debug("image has type :: " + contentType);            
        } catch (IOException e) {
            logger.error("Error at finding file's MIME type");
            return badRequest();
        }
        
        logger.debug("image successfully loaded");
        return ok(imageFile).as(contentType);
    }
    
    /* Get Path of blog-user's profile picture -- return default if null*/
    public static String getProfilePicturePath(String blogname){        
        try {
            return new UserDAO().getByBlogname(blogname).profilePicture.id 
                            + ".picture";
        } catch (Exception e) {
            return "no.picture";
        }
    }

    /* ------ Upload Picture Submit ------ */
    public static Result uploadPicture(boolean isProfilePicture) {
        
        logger.debug("uploading picture");        
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart uplPicture = body.getFile("picture_raw");
        if (uplPicture != null) {
            logger.debug("data found in request");
            String contentType = uplPicture.getContentType(); 
                     
            logger.debug("Picture content-type :: " + contentType);
            logger.debug("Check if image");
            
            if (!(contentType.split("/")[0]).equals("image")) {
                logger.error("uploaded File is not a picture");
                return badRequest(Json.newObject().put("success", false));
            }
                                                        
            /* Store picture info in DB */            
            Picture dbPicture = new Picture();
            dbPicture.user = new UserDAO().getByBlogname(session("user"));
            new PictureDAO().create(dbPicture);
                        
            /* If supposed to, assign picture to user as profile picture */
            if (isProfilePicture) {
                logger.debug("set as profile picture ... ");
                new UserDAO().updateProfilePicture(dbPicture.user, dbPicture);
            }

            /* Generate Filename */
            String filename = dbPicture.id + ".picture";
            logger.debug("Filename is going to be :: " + filename);

            /* Move File from /tmp to dary picture directory
                we use the apache commons-io Library to move the File */
            try {
                FileUtils.moveFile(uplPicture.getFile(), 
                                new File(PICTURE_FS_PATH, filename));
                logger.info("File has been saved!");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("There was an error at saving the file!");
                return badRequest(Json.newObject().put("success", false));
            }

            return ok(Json.newObject()
                        .put("success", true)
                        .put("pictureURL", "/p/" + filename));
        }

        logger.error("no data found in request");
        return ok(Json.newObject().put("success", false));

    } //-- uploadPicture
    
}
