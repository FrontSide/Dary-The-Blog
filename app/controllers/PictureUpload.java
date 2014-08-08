package controller;

import play.*;
import play.mvc.*;
import models.Picture;

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
            String fileName = uplPicture.getFilename();
            String contentType = uplPicture.getContentType(); 
            File file = uplPicture.getFile();

            logger.debug("Path of uploaded Picture :: " + file.getAbsolutePath());
            
            logger.debug("Picture content-type :: " + contentType);

            /* Store picture info in DB */
            
            Picture dbPicture = new Picture();



            /* Move File from /tmp to dary picture directory
                we use the apache commons-io Library to move the File */
            try {
                FileUtils.moveFile(file, new File("public/userdata", fileName));
                logger.info("File has been saved!");
            } catch (IOException e) {
                logger.error("There was an error at saving the file!");
                return ok(Json.newObject().put("success", false));
            }


            return ok(Json.newObject().put("success", true));
        }

        logger.error("no data found in request");
        return ok(Json.newObject().put("success", false));

    } //-- uploadPicture
}