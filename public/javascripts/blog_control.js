// controls certain elements in the blog view

// Make content-body invisible
$(document).ready(function() {
    $("#content-body-box").css("box-shadow", "none")
    $("#content-body-box").css("padding", "0px")
    $("#content-body-box").css("background", "none")
});

$(".scrollToTopLink").click(function() {
    $("html, body").animate({ scrollTop: 0 }, 2000);
});

$(".scrollToIdLink").click(function() {
    $("html, body").animate({ scrollTop: $($(this).attr('data-target')).offset().top-10 }, 2000);
});
