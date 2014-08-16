/* 
 * Controls the (Pivture) Upload
 * needed for Pictures in Posts and
 * user's Profile Pictures
 */
 
 
/* Load modal into div */
$(document).ready(function() {
    $('#picture_upload_legal').css("background-color", "#00FF00")
});
 
 /* Change text in input field -- Picture upload */
$('#picture_file').change(function() {

  console.log("file selected")
  
  $('#picture_send').addClass("btn-success");
  $('#picture_send').removeClass("btn-danger");
  $('#picture_browse_label').html("Upload <br />" + $('#picture_file').val())
  
  //If Title Input is empty put Filename in it
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
  $('#picture_upload_msg').html("<span class='fa fa-cog fa-spin'></span>"  +
        " Your picture is being uploaded! Please wait ...");

  console.log(" Picture Upload Form Data")
  console.log($('#upload_picture_form')[0])

  var formData = new FormData($('#upload_picture_form')[0]);

  console.log(formData)

  /* Ajax Code taken (and modified) 
   * from http://stackoverflow.com/questions/166221/how-can-i-upload-files-asynchronously-with-jquery/8758614#8758614
   */
  $.ajax({
        url: "/new/picture",
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
  $('#picture_upload_msg').html("Your picture was uploaded successfully!");
    
    
  /* Add the Picture to the end of the current Post if it is existing
     does nothing for Profile Picture */
  $('#content').val($('#content').val() + 
                "!["+$('#picture_title').val()
                +"](" + data['pictureURL'] + ")")
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
  $('#picture_upload_msg').html("There seems to be a problem!<br />" +
                   "Please make sure your file is actually a <b> picture </b>!" + 
                   "<br /> Otherwise, there might by a network problem!");
  $('#picture_upload_prog').css({"width" : 0});
  $('#picture_browse_label').html("Try again ...")
}

/* Picture Upload Progress Handler */
function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('#picture_upload_prog').css({"width" : e.loaded});
    }
}

