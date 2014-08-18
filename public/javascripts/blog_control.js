// controls certain elements in the blog view

// Make content-body invisible
$(document).ready(function() {

    $("#content-body-box").css("box-shadow", "none")
    $("#content-body-box").css("padding", "0px")
    $("#content-body-box").css("background", "none")
    
    /* Load Upload Modal for Picture Upload */
    $('#upload_modal_wrapper').load('/assets/html/picture_upload_modal.html');
        
});

$('#newcomment_form > textarea').keypress(function (e) {
  /* if enter in textfield */
  if (e.which == 13) {
      
    var post_id = $(this).attr("data-target")
    console.log("add comment - post id :: " + post_id)
    
    $.post("/new/comment", 
        { 
            post: post_id, 
            content: $(this).val() 
        });  
    
    $(this).val("")
    return false;
  }
});



