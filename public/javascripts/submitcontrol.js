$('#newentry_pub_inst_btn').click(function () {
    var btn = $(this)
    btn.button('Publishing ...')

    console.log("publish post...")
    $('#newentry_pub_inst_modal').modal('show')

  });