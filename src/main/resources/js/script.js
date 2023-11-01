$(document).ready(function() {
    $("#password-toggle, #confirm-password-toggle").click(function() {
        var inputField = $(this).parent().find("input");
        var passwordIcon = $(this).find("i");

        if (inputField.attr("type") === "password") {
            inputField.attr("type", "text");
            passwordIcon.removeClass("fa-eye").addClass("fa-eye-slash");
        } else {
            inputField.attr("type", "password");
            passwordIcon.removeClass("fa-eye-slash").addClass("fa-eye");
        }
    });
});
