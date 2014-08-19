// controls certain elements in the blog view

// Make content-body invisible
$(document).ready(function() {

    $("#content-body-box").css("box-shadow", "none")
    $("#content-body-box").css("padding", "0px")
    $("#content-body-box").css("background", "none")
    
    /* Load Upload Modal for Picture Upload */
    $('#upload_modal_wrapper').load('/assets/html/picture_upload_modal.html');
        
});

COMMENT_TO_DELETE = 0;
/* Delete Comment Button - Opens Modal */
$('.blog-del-com-btn').click(function () {

    $('#blog_del_comm_quest_user').html(
        Messages('COMMENT_DELETE_QUESTION_HEADER', "<b>" 
                        + $(this).attr("data-user") + "</b>"))

    COMMENT_TO_DELETE = $(this).attr("data-target");
    console.log("init delete comment - id :: " + COMMENT_TO_DELETE)
    $('#blog_del_comm_modal').modal('show')

});

/* Delete comment if confirmed in modal */
$('#blog_del_comm_send').click(function () {
    
    console.log("submit delete comment - id :: " + COMMENT_TO_DELETE)    
    $.post("/comment/delete", 
        { 
            id: COMMENT_TO_DELETE, 
        });    
    
    /* instantly delete comment (mock) */
    var comwrap_id = "#comwrap" + COMMENT_TO_DELETE
    $(comwrap_id).css("display", "none")
    
    COMMENT_TO_DELETE = 0;
    
    /* close modal */
    $('#blog_del_comm_modal').modal('hide')
        
}); 

$('#newcomment_form > textarea').keypress(function (e) {
  /* if enter in textfield */
  if (e.which == 13) {
      
    var post_id = $(this).attr("data-target")
    console.log("add comment - post id :: " + post_id)
    
    var comment_content = $(this).val()
    
    $.post("/new/comment", 
        { 
            post: post_id, 
            content: comment_content
        });  
    
    /* void comment coneten textarea */
    $(this).val("")
    
    /* instantly add comment preview (mock) */
    var wrap_id = "#cwrap" + post_id
    
    $(wrap_id).html(
        "<div class='comment-container'>" +
            "<label class='comment-header'>" +
                "" +
            "</label>" +
            "<div    class='comment-box'" +  
                    "id='newcommentid'>" +
                "<h5>" + comment_content + "</h5>" +
            "</div> " + 
        "</div>" + $(wrap_id).html())
        
    /* Initialize color transition by removing 'loading' css class */        
    setTimeout(function(){
      $('#newcommentid').addClass("loaded");
    }, 10);
        
    return false;
  }
});



