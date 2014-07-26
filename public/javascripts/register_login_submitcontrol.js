
/* Register Button - Opens Modal for License */
$('#register_btn').click(function () {
    console.log("register user...")

    /* Check if repeated password matches */
    if ($('#reg_pwd_repeat').val() != $('#reg_pwd').val()) {
      $('#signup_pwd_nomatch').css("display", "block");
      $('#reg_pwd_repeat').css("background-color", "#d9534f");
      $('#reg_pwd').css("background-color", "#d9534f");
    }
    else
      $('#register_terms_modal').modal('show')

});

/* Terms of Conditions Accept - From Modal Dialog 
    Submit Form */
$('#register_terms_accept_send').click(function () {
    $("#signup_form").submit();
}); 

/* Interactive availability check for blogname at registration */
$('#reg_blogname').keyup(function () {
  checkBlocknameAvailable();
});

$('#reg_blogname').blur(function () {
  checkBlocknameAvailable();
});

function checkBlocknameAvailable() {

  $.post("/avail/" + $('#reg_blogname').val(),{}).done( function(receivedData) {
    if (receivedData.available === false) {
      $('#reg_blogname').css("background-color", "#d9534f");
      $('#reg_blogname').css("color", "#fff");
    } 
    else {
      $('#reg_blogname').css("background-color", "#5cb85c");
      $('#reg_blogname').css("color", "#fff");
    }
  }).fail(function() {
    $('#reg_blogname').css("background-color", "#fff");
    $('#reg_blogname').css("color", "#222");
  });

}

/* On Load */
$(document).ready(function() {
  // Empty
}); 
