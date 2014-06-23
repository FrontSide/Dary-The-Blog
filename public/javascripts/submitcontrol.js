
/* Publish NOW Button - Opens Modal */
$('#newentry_pub_inst_btn').click(function () {

    console.log("publish post...")
    $('#newentry_pub_inst_modal').modal('show')



});

/* Save Entry and Publish now - From Modal Dialog 
    Submit Form */
$('#newentry_pub_inst_send').click(function () {

    /* If "Absolutely" Button clicked.. submit form with
       "publishNow" variable set true */
    $('#newenty_hidden_publish').val("true");
    $( "#newentry_form" ).submit();

}); 

/* Summernote HTML Editor (with costum toolbar) */
$(document).ready(function() {
  $('.summernote').summernote({
      height: 100,
      toolbar: [
        //[groupname, [button list]]         
        ['style', ['bold', 'italic', 'underline', 'clear']],
        ['font', ['strikethrough']],
        ['fontsize', ['fontsize']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
      ]
  });
});


