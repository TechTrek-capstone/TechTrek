$(document).ready(function () {

    let resumeId = null;
    let fsURL;
    let fsTitle;


    // Progress bar for password strength on registration
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

    // progress bar updates on every keyup here
    $(function () {
        $("#pwd").keyup(function () {
            $.strength($("#progress-bar"), $(this).val());
        });
    });

    // Tblock resume upload on btn click
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
                fsTitle = resultJSON.filesUploaded[0].filename;
                $(".resumeURL").val(fsURL);
                $(".resumeTitle").val(fsTitle);
                $("#resumeUploadTBlock").submit();
            })
    });

    // Vertical resume upload on btn click
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

    // Delete Resume Modal
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
                let student = $("#student-dropdown"), option = "<option disabled selected>Select Student</option>";
                let studentResumes = $(".student-resumes");

                for (let i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].id + "'>" + data[i].userfirstname + ' ' + data[i].lastName + "</option>";
                }

                studentResumes.empty();
                student.empty();
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


                for (let i = 0; i < data.length; i++) {
                    resumeData +=
                        "<tr><td class='w-50'><a href='" + data[i].link + "' target=_blank>" + data[i].title + "</a></td>"
                        + "<td>" + data[i].type + "</td>"
                        + "<td><button type='button' class='btn btn-primary uploadResumeRevision' value='"+ data[i].id + "'>Upload</button>";

                    // if placement has already submitted a revision, checkmark populates here
                    if (data[i].status === "Reviewed!") {
                        resumeData += "<i class='fa fa-check-circle ml-2' style='font-size: 1.5rem; color: green'></i></td>";
                    } else {
                        resumeData += "</td>";
                    }

                    // same as above, but just checking for notes
                    resumeData += "<td><button type='button' class='btn btn-primary uploadResumeNotes' data-toggle='modal' data-target='#msgModal' value='" + data[i].id + "'>Upload</button>";

                    if (data[i].placementNotes !== null) {
                        resumeData += "<i class='fa fa-check-circle ml-2' style='font-size: 1.5rem; color: green'></i></td></tr>";
                    } else {
                        resumeData += "</td></tr>";
                    }
                }

                studentResumes.empty();
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
            $("#resumeId").val(id);
            $("#uploadResumeRevision").submit();
        })
}));

// PLACEMENT - upload resume revision
$(document).on('click', '.uploadResumeNotes', (function () {
    $("#resumeNotesId").val($(this).val());
}));

// PLACEMENT - pull up modal, assign values to .sendNotes
$(document).on('click', '.resumeNotes', function () {
    $(".placement-resume-notes").html($(this).val());
});

// PLACEMENT - upload resume notes
$(document).on('click', '.sendNotes', (function () {
    $("#resumeNotesUpload").val($("#resumeNotes").val());
    $("#uploadResumeNotes").submit();
}));

