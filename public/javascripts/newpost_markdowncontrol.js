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
        
    /* Also check for chars that are displayed 
        as html code and convert to ASCII*/
        
    $("#content").val(
        $("#content").val().trim().replace(/&#(\d+);/g, function (m, n) { 
        return String.fromCharCode(n); 
        })
    )
    
}); 
