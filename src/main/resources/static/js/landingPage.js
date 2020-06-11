const $backTop = $(".back-to-top");
const isHidden = "is-hidden";

AOS.init({
    offset: 200,
    delay: 50,
    once: true
});

/*$('a[data-toggle="pill"]').on("shown.bs.tab", e => {
  AOS.refresh();
});*/

$(window).on("scroll", function() {
    const $this = $(this);
    if ($this.scrollTop() + $this.height() == $(document).height()) {
        $backTop.removeClass(isHidden);
    } else {
        $backTop.addClass(isHidden);
    }
});

$backTop.on("click", () => {
    $("html, body").animate({ scrollTop: 0 }, "slow");
    return false;
});

let i = 0;
let txt = 'TechTrek '; /* The text */
let speed = 150; /* The speed/duration of the effect in milliseconds */

function typeWriter() {
    if (i < txt.length) {
        document.getElementById("techtrek-title").innerHTML += txt.charAt(i);
        i++;
        setTimeout(typeWriter, speed);
    }
}

setTimeout(function() {
    typeWriter();
}, 750);

setTimeout(function() {
    $('#techtrek-subtitle').fadeTo(1000,1.5);
}, 2000);