
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

  $('#post_content').wysihtml5({
        "font-styles": true, //Font styling, e.g. h1, h2, etc. Default true
        "emphasis": true, //Italics, bold, etc. Default true
        "lists": true, //(Un)ordered lists, e.g. Bullets, Numbers. Default true
        "html": false, //Button which allows you to edit the generated HTML. Default false
        "link": true, //Button to insert a link. Default true
        "image": true, //Button to insert an image. Default true,
        "color": false, //Button to change color of font
        "size": 'sm' //Button size like sm, xs etc.
    }); 

  $('#post_content').css("height", "auto");
  $('#post_content').resizable({handles: "se"});

});


