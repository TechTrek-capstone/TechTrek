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
    let fsTitle;

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
                console.log(result);
                // store resume url in variable, pass that variable as a value to the view
                fsURL = resultJSON.filesUploaded[0].url;
                fsTitle = resultJSON.filesUploaded[0].filename;
                $(".resumeURL").val(fsURL);
                $(".resumeTitle").val(fsTitle);
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
                fsTitle = resultJSON.filesUploaded[0].filename;
                $(".resumeURL").val(fsURL);
                $(".resumeTitle").val(fsTitle);

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

    // DROPDOWN - populate student dropdown based off of cohort dropdown
    $("#cohort-dropdown").change(function () {
        let cohortId = $(this).val();

        $.ajax({
            type: 'GET',
            url: '/resume/' + cohortId,
            success: [function (data) {
                let student = $("#student-dropdown"), option = "Select Student";
                // student.empty();

                for (let i = 0; i < data.length; i++) {
                    option = option + "<option value='" + data[i].id + "'>" + data[i].userfirstname + ' ' + data[i].lastName + "</option>";

                }
                student.append(option);
            }],
            error: function () {
                alert("error");
            }

        });
    });

    // DROPDOWN - populate resume links based off of student dropdown
    $("#student-dropdown").change(function () {
        let studentId = $(this).val();

        $.ajax({
            type: 'GET',
            url: '/resume/student/' + studentId,
            success: [function (data) {
                console.log(data);
                let studentResumes = $(".student-resumes");
                let resumeData;
                let noResume = "<tr colspan='3'><td>No Resume Uploaded</td></tr>";

                for (let i = 0; i < data.length; i++) {
                    resumeData = resumeData
                        + "<tr><td><a href='" + data[i].link + "' target=_blank'>" + data[i].title + "</a></td>"
                        + "<td>'" + data[i].type + "'</td>"
                        + "<td><button type='button' class='btn btn-primary uploadResumeRevision' value='" + data[i].id + "'>Upload Revision</button></td>"
                        + "<td><button type='button' class='btn btn-primary uploadResumeNotes' value='" + data[i].id + "'>Upload Notes</button></td></tr>";
                }
                studentResumes.append(resumeData);
            }],
            error: function () {
                alert("error");
            }

        });
    });
});

// PLACEMENT - upload resume revision
$(document).on('click', '.uploadResumeRevision', (function () {
    let id = $(this).val();
    let client = filestack.init(fileStackKey);

    client
        .pick({
            maxFiles: 1
        })
        .then(function (result) {
            let resultJSON = JSON.parse(JSON.stringify(result));

            fsURL = resultJSON.filesUploaded[0].url;
            $("#resumeRevisionUpload").val(fsURL);
            $("#resumeRevisionId").val(id);
            $("#uploadResumeRevision").submit();
        })
}));