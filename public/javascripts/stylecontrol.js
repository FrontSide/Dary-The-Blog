/* (C) David Rieger 2014 for www.dary.com 
 * stlecontrol.js
 *
 * Control several style elements and animations
 * included in templ.scala.html
/*


/* Scroll Animation */
$(".scrollToTopLink").click(function() {
    $("html, body").animate({ scrollTop: 0 }, 1000);
});

$(".scrollToIdLink").click(function() {
    $("html, body").animate({ scrollTop: $($(this).attr('data-target')).offset().top-10 }, 1000);
});

