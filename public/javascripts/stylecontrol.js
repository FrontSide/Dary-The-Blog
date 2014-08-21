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

    var targetID = $(this).attr('data-target')
    console.log("scroll to :: " + targetID)
            
    /* For a visual color-transition effect 
        remove the loaded class (if existing) and load it again */
     $(targetID).removeClass('loaded');

    $("html, body").animate({ scrollTop: $(targetID).offset().top-10 }, 1000);
    
    /* Initialize color transition by removing 'loading' css class */        
    setTimeout(function(){
      $(targetID).addClass('loaded');
    }, 500);
    
});

