
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

/* On Load */
$(document).ready(function() {
  // Empty
}); 
