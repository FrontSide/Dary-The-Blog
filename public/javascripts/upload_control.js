/* 
 * Controls the (Pivture) Upload
 * needed for Pictures in Posts and
 * user's Profile Pictures
 */
 
 /*
  * Some settings-variables for the upload
  * different depending on whether it is a profile picture or not
  */
var UPLOAD_POST_URL; //url to send form to
var IS_PROFILE_PICTURE; //boolean 

/* Initialitzing function */
function initPostPictureUpload() {
    console.log("initialize picture upload [article picture]")
    UPLOAD_POST_URL = "/new/picture"
    IS_PROFILE_PICTURE = false;
}

function initProfilePictureUpload() {
    console.log("initialize picture upload [profile picture]")
    UPLOAD_POST_URL = "/new/profilepicture"
    IS_PROFILE_PICTURE = true;
    $('#picture_title_wrapper').css("display", "none")
}

/* Injects the i18n Strings into the plain picture_upload_modal.html */
function injectStrings() {
    $('#upl_pic_title').html(Messages('JS_UPLOAD_TITLE'))
    $('#picture_upload_msg').html(Messages('JS_UPLOAD_MESSAGE'))
    $('#upl_pic_title_label').html(Messages('TITLE'))
    $('#upl_pic_browse_label').html(Messages('JS_UPLOAD_BROWSE'))
    $('#picture_browse_label').html(Messages('JS_UPLOAD_NOFILES'))
    $('#upl_pic_addinfo_title').html(Messages('JS_UPLOAD_ADDINFO_TITLE'))
    $('#upl_pic_addinfo_body').html(Messages('JS_UPLOAD_ADDINFO_BODY'))
    $('#upl_pic_cancel_btn').html(Messages('CANCEL'))
}

/* Check URL path and initialize upload modal */
$(document).ready(function() {
    var currentLocation = window.location;
    if ( currentLocation.pathname === "/new" 
        || currentLocation.pathname.indexOf("/edit/") > -1) {
        initPostPictureUpload()
    } else {
        initProfilePictureUpload()
    }
    injectStrings();
});

/* ****************************** */

 /* Change text in input field -- Picture upload */
$('#picture_file').change(function() {

  console.log("file selected")
  
  $('#picture_send').addClass("btn-success");
  $('#picture_send').removeClass("btn-danger");
  $('#picture_browse_label').html("Upload <br />" + $('#picture_file').val())
  
  //If Title Input is empty put Filename in it
  //not used for profile picture
  if ($('#picture_title').val() === '') {
    $('#picture_title').val($('#picture_file').val())
  }
  
  //Activate UPLOAD Button
  $('#picture_send').css("display", "block")
  
});

/* Upload Picture Button */
$('#picture_send').click(function() {
  tryUploadPicture();
});

function tryUploadPicture() {

  $('#picture_upload_msg').removeClass("alert-success");
  $('#picture_upload_msg').removeClass("alert-danger");  
  $('#picture_upload_msg').removeClass("alert-info");      
  $('#picture_upload_msg').addClass("alert-warning");
  
  $('#picture_upload_msg').html("<span class='fa fa-cog fa-spin'></span> " +
        Messages('JS_UPLOAD_PROGRESS'));

  console.log(" Picture Upload Form Data")
  console.log($('#upload_picture_form')[0])

  var formData = new FormData($('#upload_picture_form')[0]);

  console.log(formData)

  /* Ajax Code taken (and modified) 
   * from http://stackoverflow.com/questions/166221/how-can-i-upload-files-asynchronously-with-jquery/8758614#8758614
   */
  $.ajax({
        url: UPLOAD_POST_URL,
        type: 'POST',
        //Custom HTTP Request
        xhr: function() {
            var myXhr = $.ajaxSettings.xhr();
            if(myXhr.upload){
                myXhr.upload.addEventListener('progress', 
                        progressHandlingFunction, false); 
                        // For handling the progress of the upload
            }
            return myXhr;
        },
        //Events
        beforeSend: beforeSendHandler,
        success: completeHandler,
        error: errorHandler,
        // Form data
        data: formData,
        //Options to tell jQuery not to process data 
        //or worry about content-type.
        cache: false,
        contentType: false,
        processData: false
    });      
}

/* Picture Upload Event Handlers */
function beforeSendHandler() {
  $('#picture_upload_prog_wrapper').removeAttr("hidden");
}

//Access Picture URL via "data" JSON response
function completeHandler(data) {
  $('#picture_send').css("background-color", "#5cb85c");
  $('#picture_send').addClass("btn-success");
  $('#picture_send').removeClass("btn-danger");
  $('#picture_upload_msg').removeClass("alert-warning");
  $('#picture_upload_msg').removeClass("alert-danger");  
  $('#picture_upload_msg').removeClass("alert-info");
  $('#picture_upload_msg').addClass("alert-success");
  $('#picture_upload_msg').html(Messages('JS_UPLOAD_SUCCESS'));
    
    
  /* Add the Picture to the end of the current Post if it is existing
     does nothing for Profile Picture */
  if (!IS_PROFILE_PICTURE) {
      $('#content').val($('#content').val() + 
                    "!["+$('#picture_title').val()
                    +"](" + data['pictureURL'] + ")")
  }
  /* --- */

  /* Close Modal */
  $('#upload_picture_modal').modal('hide')  
  
}

function errorHandler() {
  $('#picture_send').removeClass("btn-success");
  $('#picture_send').addClass("btn-danger");
  $('#picture_upload_msg').removeClass("alert-warning");
  $('#picture_upload_msg').removeClass("alert-success");
  $('#picture_upload_msg').removeClass("alert-info");
  $('#picture_upload_msg').addClass("alert-danger");  
  $('#picture_upload_msg').html(Messages('JS_UPLOAD_PROBLEM'));
  $('#picture_upload_prog').css({"width" : 0});
  $('#picture_browse_label').html(Messages('JS_UPLOAD_TRYAGAIN'))
}

/* Picture Upload Progress Handler */
function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('#picture_upload_prog').css({"width" : e.loaded});
    }
}

