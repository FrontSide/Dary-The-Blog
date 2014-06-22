
// Publish NOW Button - Opens Modal
$('#newentry_pub_inst_btn').click(function () {
    var btn = $(this)
    btn.button('Publishing ...')

    console.log("publish post...")
    $('#newentry_pub_inst_modal').modal('show')

});

/* Save Entry and Publish now - From Modal Dialog */
$('#newentry_pub_inst_send').click(function () {
    var btn = $(this)
    btn.button('Publishing ...')

    jsRoutes.controllers.Application.authenticate().ajax({
        data : $("#my_form").serialize(),
        success : function(data) {
        // ...
        },
        error : function(err) {
        // ...
        }
    });
}); 