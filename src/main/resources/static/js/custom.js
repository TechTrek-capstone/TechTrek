

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
        // set file type that is uploaded
        maxFiles: 1,
    }).then(function (result) {
        let img = new Image();
        let resultJSON = JSON.parse(JSON.stringify(result));

        // store img handle in variable
        let fsHandle = resultJSON.filesUploaded[0].handle;

        // store img url in variable
        let fsURL = resultJSON.filesUploaded[0].url;

        // print msg after successful upload here
        $(".success").html("You've successfully uploaded your resume! Please wait for Placement to review.");

        // store url inside download link
        $(".downloadLink").attr("href", fsURL);

        // bunk - don't need to show pdf of resume
        // img.src = "https://cdn.filestackcontent.com/" + fsHandle;
        // $(".resume").append(img);
    });
});