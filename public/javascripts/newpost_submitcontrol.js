
/* Markdown is converted to HTML on save */

/* Activate Save and Publish Button only when Title is entered */
$('#title').keyup(function () {

    if ($('#title').val().trim() != "") {
        $('#newpost_save_btn').removeAttr("disabled");
        $('#newpost_pub_inst_btn').removeAttr("disabled");
    } else {
        $('#newpost_save_btn').attr("disabled","disabled");
        $('#newpost_pub_inst_btn').attr("disabled","disabled");
    }

});


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

/* Feature Post Trigger */
$('#newpost_feature_btn').click(function () {
        if ($('#newpost_hidden_featured').val() == "false") {
            $('#newpost_hidden_featured').val("true");
            $('#newpost_feature_btn').html("<span class='fa fa-star'> </span>" + 
                " Feature Post");
            $('#newpost_feature_btn').addClass("btn-warning")
        } else {
            $('#newpost_hidden_featured').val("false");
            $('#newpost_feature_btn').html("<span class='fa fa-star-o'> </span>" + 
                " Feature Post");
            $('#newpost_feature_btn').removeClass("btn-warning")        
        }
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

  $('#newpost_picture_upload_msg').removeClass("alert-success");
  $('#newpost_picture_upload_msg').removeClass("alert-danger");  
  $('#newpost_picture_upload_msg').removeClass("alert-info");      
  $('#newpost_picture_upload_msg').addClass("alert-warning");
  $('#newpost_picture_upload_msg').html("<span class='fa fa-cog fa-spin'></span>"  +
        " Your picture is being uploaded! Please wait ...");

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
  $('#newpost_picture_upload_prog_wrapper').removeAttr("hidden");
}

//Access Picture URL via "data" JSON response
function completeHandler(data) {
  $('#newpost_picture_browse_label').css("background-color", "#5cb85c");
  $('#newpost_picture_browse_label').css("color", "#fff");
  $('#newpost_picture_upload_msg').removeClass("alert-warning");
  $('#newpost_picture_upload_msg').removeClass("alert-danger");  
  $('#newpost_picture_upload_msg').removeClass("alert-info");
  $('#newpost_picture_upload_msg').addClass("alert-success");
  $('#newpost_picture_upload_msg').html("Your picture was uploaded successfully!");
    
  $('#content').val($('#content').val() + 
                "![UNTITLED PICTURE](" + data['pictureURL'] + ")")
  
}

function errorHandler() {
  $('#newpost_picture_browse_label').css("background-color", "#d9534f");
  $('#newpost_picture_browse_label').css("color", "#fff");
  $('#newpost_picture_upload_msg').removeClass("alert-warning");
  $('#newpost_picture_upload_msg').removeClass("alert-success");
  $('#newpost_picture_upload_msg').removeClass("alert-info");
  $('#newpost_picture_upload_msg').addClass("alert-danger");
  $('#newpost_picture_upload_msg').html("There seems to be a problem!<br />" +
                   "Please make sure your file is actually a <b> picture </b>!" + 
                   "<br /> Otherwise, there might by a network problem!");
  $('#newpost_picture_upload_prog').css({"width" : 0});
}

/* Picture Upload Progress Handler */
function progressHandlingFunction(e){
    if(e.lengthComputable){
        $('#newpost_picture_upload_prog').css({"width" : e.loaded});
    }
}

/* On Load */
$(document).ready(function() {
  if ($('#title').val().trim() != "") {
      $('#newpost_save_btn').removeAttr("disabled");
      $('#newpost_pub_inst_btn').removeAttr("disabled");
  }
}); 
