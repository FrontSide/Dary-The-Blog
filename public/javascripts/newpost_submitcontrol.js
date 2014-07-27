
/* Markdown is converted to HTML on save */

/* Publish NOW Button - Opens Modal */
$('#newpost_pub_inst_btn').click(function () {

    console.log("publish post...")
    $('#newpost_pub_inst_modal').modal('show')

});

/* Save Entry and Publish now - From Modal Dialog 
    Submit Form */
$('#newpost_pub_inst_send').click(function () {

    /* Convert */
    $('#content').val(markdown.toHTML($('#content').val()));

    /* If "Absolutely" Button clicked.. submit form with
       "publishNow" variable set true */
    $('#newpost_hidden_publish').val("true");
    $("#newpost_form").submit();

}); 

/* Save Without Publish Button */
$('#newpost_save_btn').click(function () {
  /* Convert */
  $('#content').val(markdown.toHTML($('#content').val()));
  $("#newpost_form").submit();
});

/* Change text in input field -- Picture upload */
$('#newpost_picture_file').change(function() {
  $('#newpost_picture_browse_label').html($('#newpost_picture_file').val())
});

/* Upload Picture Button */
$('#newpost_picture_send').click(function() {
  tryUploadPicture();
});

function tryUploadPicture() {

  $('#newpost_picture_upload_msg').addClass("alert-warning");
  $('#newpost_picture_upload_msg').html("Your picture is being uploaded! Please wait ...");

  console.log(" Picture Upload Form Data")
  console.log($('#newpost_upload_picture_form')[0])

  var formData = new FormData($('#newpost_upload_picture_form')[0]);

  console.log(" As Form Data")

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
                myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // For handling the progress of the upload
            }
            return myXhr;
        },
        //Events
        beforeSend: beforeSendHandler,
        success: completeHandler,
        error: errorHandler,
        // Form data
        data: formData,
        //Options to tell jQuery not to process data or worry about content-type.
        cache: false,
        contentType: false,
        processData: false
    });      
}

/* Picture Upload Event Handlers */
function beforeSendHandler() {  
}

function completeHandler() {  
  $('#newpost_picture_browse_label').css("background-color", "#5cb85c");
  $('#newpost_picture_browse_label').css("color", "#fff");
  $('#newpost_picture_upload_msg').removeClass("alert-warning");
  $('#newpost_picture_upload_msg').removeClass("alert-danger");
  $('#newpost_picture_upload_msg').addClass("alert-success");
  $('#newpost_picture_upload_msg').html("Your picture was uploaded successfully!");
}

function errorHandler() {
  $('#newpost_picture_browse_label').css("background-color", "#d9534f");
  $('#newpost_picture_browse_label').css("color", "#fff");
  $('#newpost_picture_upload_msg').removeClass("alert-warning");
  $('#newpost_picture_upload_msg').removeClass("alert-success");
  $('#newpost_picture_upload_msg').addClass("alert-danger");
  $('#newpost_picture_upload_msg').html("Mhhh. There seems to be a network problem!");
}

/* Picture Upload Progress Handler */
function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('#newpost_picture_upload_prog').css({"width" : e.loaded});
    }
}

/* On Load */
$(document).ready(function() {
  // Empty
}); 
