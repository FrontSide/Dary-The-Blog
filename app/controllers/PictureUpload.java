package controllers;

import play.mvc.*;
import models.Picture;
import dao.*;

import play.Logger;

import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import play.libs.Json;

public class PictureUpload extends Controller {

    final static Logger.ALogger logger = Logger.of(PictureUpload.class);

    /* ------ Upload Picture Submit ------ */
    public static Result uploadPicture() {

        /* TODO
           Save Picture (generate Filename)
           Store Picture info in DB
           Add Picture to Article when uploaded
           Several checks (as appropriate)
           -- ContentType Check !
        */

        /*** NEEEDS REFACTORING ***///

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

            /* Generate Filename */
            String filename = dbPicture.id + ".picture";
            logger.debug("Filename is going to be :: " + filename);

            /* Move File from /tmp to dary picture directory
                we use the apache commons-io Library to move the File */
            try {
                FileUtils.moveFile(uplPicture.getFile(), 
                                new File("public/userdata", filename));
                logger.info("File has been saved!");
            } catch (IOException e) {
                logger.error("There was an error at saving the file!");
                return badRequest(Json.newObject().put("success", false));
            }

            return ok(Json.newObject()
                        .put("success", true)
                        .put("pictureURL", "/assets/userdata/" + filename));
        }

        logger.error("no data found in request");
        return ok(Json.newObject().put("success", false));

    } //-- uploadPicture
}
