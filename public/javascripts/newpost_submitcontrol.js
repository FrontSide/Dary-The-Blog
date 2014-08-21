
/*
 *
 * Controls actions on the newpost view
 *
 */

$(document).ready(function() {
    /* Load Upload Modal for Picture Upload */
    $('#upload_modal_wrapper').load('/assets/html/picture_upload_modal.html');
});

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
            $('#newpost_feature_btn').html("<span class='fa fa-star'> </span> " + 
                Messages('JS_FEATURED_ARTICLE'));
            $('#newpost_feature_btn').addClass("btn-warning")
        } else {
            $('#newpost_hidden_featured').val("false");
            $('#newpost_feature_btn').html("<span class='fa fa-star-o'> </span> " + 
                Messages('FEATURE_THIS_POST'));
            $('#newpost_feature_btn').removeClass("btn-warning")        
        }
        
});

/* Delete Article */
$('#newpost_delete_btn_wrapper').mouseenter(function() {
    if($('#newpost_delete_btn').prop("disabled")) {
        $('#newpost_delete_info').css('display', 'block')
    }    
});

$('#newpost_delete_btn_wrapper').mouseout(function() {
    $('#newpost_delete_info').css('display', 'none')
});

$("#content").keyup(function(){
    changeDeleteButtonEnabled()
});

$("#title").keyup(function(){
   changeDeleteButtonEnabled()
}); 

function changeDeleteButtonEnabled() {
     if ($("#content").val().trim() === '' && $('#title').val().trim() === '') {
        $('#newpost_delete_btn').removeAttr("disabled")
    } else {
        $('#newpost_delete_btn').attr("disabled", "disabled")
    }
}

$('#newpost_delete_btn').click(function(){
    $('#newpost_delete_modal').modal('show')
});


/**/

/* On Load */
$(document).ready(function() {
  if ($('#title').val().trim() != "") {
      $('#newpost_save_btn').removeAttr("disabled");
      $('#newpost_pub_inst_btn').removeAttr("disabled");
  }
}); 
