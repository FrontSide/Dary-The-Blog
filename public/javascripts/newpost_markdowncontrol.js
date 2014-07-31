// Markdown Control for NewPost Form
//This JS defines certein attributed for the markdown field in the newpost form
//e.g. adds button to upload picture

/* On Load */
$(document).ready(function() {

    /* Check if incoming Post Content is existing if so
       (it's most certainly HTML) convert it from HTML to Markdown
       This is needed for editing an already saved Post since Posts are
       converted to HTML the moment they're saved */
    if($("#content").val().trim() != "") {
      $("#content").val(toMarkdown($("#content").val()));
    }

    $("#content").markdown({
        additionalButtons: [
            [{
                name: "groupCustom",
                data: [{
                    name: "uploadPicture",
                    toggle: true, // this param only take effect if you load bootstrap.js
                    title: "Upload a picture",
                    icon: "fa fa-picture-o",
                    callback: function(e){

                        //Show modal
                        $('#newpost_upload_picture_modal').modal('show')

                      /* Replace selection with some drinks
                      var chunk, 
                          cursor,
                          selected = e.getSelection(), 
                          content = e.getContent(),
                          drinks = ["Heinekken", "Budweiser",
                                    "Iron City", "Amstel Light",
                                    "Red Stripe", "Smithwicks",
                                    "Westvleteren", "Sierra Nevada",
                                    "Guinness", "Corona", "Calsberg"],
                          index = Math.floor((Math.random()*10)+1)

                    // Give random drink
                    chunk = drinks[index]

                    // transform selection and set the cursor into chunked text
                    e.replaceSelection(chunk)
                    cursor = selected.start

                    // Set the cursor
                    e.setSelection(cursor,cursor+chunk.length)
                    */
                }
              }]
            }]
        ]
    })
}); 