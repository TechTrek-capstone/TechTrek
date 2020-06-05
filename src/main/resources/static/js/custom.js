$(document).ready(function () {
    let resumeId = null;
    let fsURL;

// PROGRESS BAR TEST
    $.strength = function (element, password) {
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

    $(function () {
        $("#pwd").keyup(function () {
            $.strength($("#progress-bar"), $(this).val());
        });
    });
// END PROGRESS BAR


    $("#uploadResume").click(function () {
        let client = filestack.init(fileStackKey);
        resumeId = $("#uploadResume").val();

        client
            .pick({
            maxFiles: 1,
            })
            .then(function (result) {
            let resultJSON = JSON.parse(JSON.stringify(result));

            // store resume url in variable, pass that variable as a value to the view
            fsURL = resultJSON.filesUploaded[0].url;
            $("#resumeURL").val(fsURL);

            // print msg after successful upload here
            $(".resume-status").html("Pending Review").css;

            // print placement msg
            $(".placement-msg").html("You've successfully uploaded your resume! Please wait for Placement to review.");

        $("#urlForm").submit(changeTest());
        });
    });
    
    let changeTest = function() {
        $(".resume-status").html("Pending Review").css;
        $(".placement-msg").html("You've successfully uploaded your resume! Please wait for Placement to review.");
    }
});