
/* Markdown is converted to HTML on save */

/* Publish NOW Button - Opens Modal */
$('#newentry_pub_inst_btn').click(function () {

    console.log("publish post...")
    $('#newentry_pub_inst_modal').modal('show')

});

/* Save Entry and Publish now - From Modal Dialog 
    Submit Form */
$('#newentry_pub_inst_send').click(function () {

    /* Convert */
    $('#content').val(markdown.toHTML($('#content').val()));

    /* If "Absolutely" Button clicked.. submit form with
       "publishNow" variable set true */
    $('#newenty_hidden_publish').val("true");
    $("#newentry_form").submit();

}); 

/* Save Without Publish Button */
$('#newentry_save_btn').click(function () {
  /* Convert */
  $('#content').val(markdown.toHTML($('#content').val()));
  $("#newentry_form").submit();
});

/* On Load */
$(document).ready(function() {
  // Empty
}); 
