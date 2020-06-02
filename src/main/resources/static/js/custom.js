

// PROGRESS BAR TEST
$.strength = function(element, password) {
    var desc = [{
    'width': '0px'
}, {
    'width': '20%'
}, {
    'width': '40%'
}, {
    'width': '60%'
}, {
    'width': '80%'
}, {
    'width': '100%'
}];
var descClass = ['', 'progress-bar-danger', 'progress-bar-danger', 'progress-bar-warning', 'progress-bar-success', 'progress-bar-success'];
var score = 0;

if (password.length > 6) {
    score++;
}

if ((password.match(/[a-z]/)) && (password.match(/[A-Z]/))) {
    score++;
}

if (password.match(/\d+/)) {
    score++;
}

if (password.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/)) {
    score++;
}

if (password.length > 10) {
    score++;
}

element.removeClass(descClass[score - 1]).addClass(descClass[score]).css(desc[score]);
};

$(function() {
    $("#pwd").keyup(function() {
        $.strength($("#progress-bar"), $(this).val());
    });
});
// END PROGRESS BAR


$("#uploadResume").click(function () {
    var client = filestack.init(fileStackKey);
    client.pick({
        maxFiles: 1,
    }).then(function (result) {//Taking the result object in as 'result'
        //Putting the result in a string, and printing it to the console
        console.log(JSON.stringify(result));
    });
});