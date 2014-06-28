
/* Register Button - Opens Modal for License */
$('#register_btn').click(function () {

    console.log("register user...")
    $('#register_terms_modal').modal('show')

});

/* Terms of Conditions Accept - From Modal Dialog 
    Submit Form */
$('#register_terms_accept_send').click(function () {
    $("#signup_form").submit();
}); 

/* Check Password match while typing
$('#xx register password on change check').xxxx(function () {

}); */

/* On Load */
$(document).ready(function() {
  // Empty
}); 
