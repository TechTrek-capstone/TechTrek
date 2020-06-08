$(document).ready(function () {

    let changeTest = function () {
        $(".resume-status").html("Pending Review").css;
        $(".placement-msg").html("You've successfully uploaded your resume! Please wait for Placement to review.");
    };

    if ($(".resume-row").val() !== null) {
        changeTest();
    }

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

    // UPLOAD TBLOCK RESUME
    $(".uploadTBlockResume").click(function () {
        let client = filestack.init(fileStackKey);

        client
            .pick({
                maxFiles: 1
            })
            .then(function (result) {
                let resultJSON = JSON.parse(JSON.stringify(result));

                // store resume url in variable, pass that variable as a value to the view
                fsURL = resultJSON.filesUploaded[0].url;
                $(".resumeURL").val(fsURL);
                $("#resumeUploadTBlock").submit();
            })
    });

    // UPLOAD VERTICAL RESUME
    $(".uploadVerticalResume").click(function () {
        let client = filestack.init(fileStackKey);

        client
            .pick({
                maxFiles: 1
            })
            .then(function (result) {
                let resultJSON = JSON.parse(JSON.stringify(result));

                // store resume url in variable, pass that variable as a value to the view
                fsURL = resultJSON.filesUploaded[0].url;
                $(".resumeURL").val(fsURL);
                $("#resumeUploadVertical").submit();
            })
    });

    // DELETE RESUME
    $('#deleteResumeModal').on('show.bs.modal', function (e) {
        var button = $(e.relatedTarget); // Button that triggered the modal
        var resumeId = button.data('id'); // Extract info from data-* attributes

        // Update the modal's content
        var modal = $(this);
        modal.find('#deleteResumeId').val(resumeId);
    });
});

