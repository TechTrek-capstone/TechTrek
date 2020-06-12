$(document).ready(function () {

    // Globally-scoped variables for student resume submissions
    let fsURL;
    let fsTitle;

    // START PROGRESS BAR #####################################
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
    // END PROGRESS BAR ####################################



    // Student resume upload on tblock / vertical btn click
    $(".uploadTBlockResume, .uploadVerticalResume").click(function () {
        let client = filestack.init(fileStackKey);
        let resumeType = $(this).val(); // get type of resume from btn value

        client
            .pick({
                maxFiles: 1
            })
            .then(function (result) {
                let resultJSON = JSON.parse(JSON.stringify(result));

                fsURL = resultJSON.filesUploaded[0].url; // store resume url in variable
                fsTitle = resultJSON.filesUploaded[0].filename; // same as above, but with the filename

                $(".resumeURL").val(fsURL); // pass variable value to '.resumeURL' for form submission
                $(".resumeTitle").val(fsTitle); // same as above, but with filename

                uploadStudentResume(resumeType); // call upload function
            })
    });

    // Upload resume - functions checks the type of resume, and submits the appropriate form
    function uploadStudentResume(resumeType) {
        if (resumeType === "tblock") {
            $("#resumeUploadTBlock").submit();
        } else {
            $("#resumeUploadVertical").submit();
        }
    }

    // Delete Resume Modal
    $('#deleteResumeModal').on('show.bs.modal', function (e) {
        let button = $(e.relatedTarget); // Button that triggered the modal
        let resumeId = button.data('id'); // Extract info from data-* attributes

        // Update the modal's content
        let modal = $(this);
        modal.find('#deleteResumeId').val(resumeId); // sets the modal's hidden input to resumeId, let's the controller know which resume to delete
    });

    // Ajax GET to populate student dropdown based off of cohort
    function studentDropdownAJAX(cohortId) {
        $.ajax({
            type: 'GET',
            url: '/resume/' + cohortId,
            success: [function (data) {
                let student = $("#student-dropdown"), option = "<option disabled selected>Select Student</option>"; // targeting student dropdown

                for (let i = 0; i < data.length; i++) {
                    // generating the html for our view - every option has a value with the student's id
                    option += "<option value='" + data[i].id + "'>" + data[i].userfirstname + ' ' + data[i].lastName + "</option>";
                }

                student.empty(); // emptying the dropdown so we don't create duplicate entries
                student.append(option); // appending the options to our student dropdown
            }],
            error: function () {
                alert("Error finding students.");
            }
        });
    }

    // function is called when either the cohort or student dropdown is selected
    function populateResumesTableAJAX(cohortOrStudent, Id) {

        let url = "";
        // figuring out which dropdown was clicked, and setting the appropriate url
        if (cohortOrStudent === "cohort") {
            url = 'resume/cohort/' + Id;
        } else {
            url = 'resume/student/' + Id;
        }

        // Ajax GET to populate table
        $.ajax({
            type: 'GET',
            url: url,
            success: function (data) {
                let studentResumes = $(".student-resumes");
                let resumeData;


                for (let i = 0; i < data.length; i++) {
                    resumeData +=
                        "<tr><td class='w-50'><a href='" + data[i].link + "' target=_blank>" + data[i].title + "</a></td>"
                        + "<td>" + data[i].type + "</td>"
                        + "<td><button type='button' class='btn uploadResumeRevision' value='" + data[i].id + "'>";

                    // if placement has already submitted a revision, checkmark populates here
                    if (data[i].status === "Reviewed!") {
                        resumeData += "<i class = 'fas fa-check-circle upload-success-circle'></i></button></td>";
                    } else {
                        resumeData += "<i class = 'fas fa-arrow-circle-up need-to-upload'></i></button></td>";
                    }

                    // same as above, but just checking for notes
                    resumeData += "<td><button type='button' class='btn uploadResumeNotes' data-toggle='modal' data-target='#msgModal' value='" + data[i].id + "'>";

                    if (data[i].placementNotes !== null) {
                        resumeData += "<i class='fa fa-check-circle upload-success-circle'></i></button></td></tr>";
                    } else {
                        resumeData += "<i class = 'fas fa-arrow-circle-up need-to-upload'></i></button></td></tr>";
                    }
                }

                studentResumes.empty();
                studentResumes.append(resumeData);
            },
            error: function () {
                alert("Error loading table.");
            }
        });
    }

    // DROPDOWN - Populate 2nd dropdown and resume for cohort
    $("#cohort-dropdown").change(function () {
        let cohortId = $(this).val();
        studentDropdownAJAX(cohortId);
        populateResumesTableAJAX("cohort", cohortId);
    });

    // DROPDOWN - populate resume links based off of student dropdown
    $("#student-dropdown").change(function () {
        let studentId = $(this).val();
        populateResumesTableAJAX("student", studentId);
    });
});

// Placement - submit resume revisions
$(document).on('click', '.uploadResumeRevision', (function () {
    let client = filestack.init(fileStackKey);
    let resumeId = $(this).val();
    let btn = $(this);
    let fsURL;

    client
        .pick({
            maxFiles: 1
        })
        .then(function (result) {
            let resultJSON = JSON.parse(JSON.stringify(result));

            fsURL = resultJSON.filesUploaded[0].url;

        })
        .then(function () {
            // call ajax POST to submit data
            submitResumeRevision(fsURL, resumeId);
        }).then(function() {
            btn.replaceWith("<button type='button' class='btn uploadResumeRevision' value='"+resumeId+"'><i class='fa fa-check-circle upload-success-circle'></i></button>");
    })
}));

// Ajax POST request to submit resume revision
function submitResumeRevision(fsURL, resumeId) {
    let data = {
        urlORNotes: fsURL,
        resumeId: resumeId
    };
    let token = $("meta[name='_csrf']").attr("content");

    $.ajax({
        type: "POST",
        url: "resume/revision",
        headers: {"X-CSRF-TOKEN": token},
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data)
    })
}

// STUDENT - pull up modal, assign placement notes   html here
$(document).on('click', '.resumeNotes', function() {
    $(".placement-resume-notes").html($(this).val());
});

let btn;
// PLACEMENT - btn pulls up #msgModal - we're assigning the modal's send btn-value to this btn's value (resume id)
$(document).on('click', '.uploadResumeNotes', (function () {
    $("#placementResumeNotesId").val($(this).val());
    btn = $(this);
}));

// PLACEMENT - upload resume notes
$(document).on('click', '.sendNotes', (function () {
    let resumeId = $(this).val();
    let resumeNotes = $("#placement-resume-notes").val();
    $("#placement-resume-notes").val('');

    submitStudentNotes(resumeNotes, resumeId);
    btn.replaceWith("<button type='button' class='btn uploadResumeNotes' data-toggle='modal' data-target='#msgModal' value='"+resumeId+"'><i class='fa fa-check-circle upload-success-circle'></i></button>");

}));

// PLACEMENT - Ajax POST to submit student notes on their resume
function submitStudentNotes(resumeNotes, resumeId) {
    let token = $("meta[name='_csrf']").attr("content");
    let data = {
        urlORNotes: resumeNotes,
        resumeId: resumeId
    };

    $.ajax({
        type: "POST",
        url: "resume/notes",
        headers: {"X-CSRF-TOKEN": token},
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data),
    })
}
