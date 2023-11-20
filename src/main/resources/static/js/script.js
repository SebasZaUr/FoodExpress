$(document).ready(function() {
    // Cambiar la visibilidad de la contraseña
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

    // Mostrar alertas personalizadas
    const alertPlaceholder = document.getElementById('liveAlertPlaceholder');

    const appendAlert = (message, type) => {
        const wrapper = document.createElement('div');
        wrapper.innerHTML = `
            <div class="alert alert-${type} alert-dismissible" role="alert">
                <div>${message}</div>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        `;

        // Limpiar alertas anteriores
        alertPlaceholder.innerHTML = '';
        alertPlaceholder.append(wrapper);
    };

    const alertTrigger = document.getElementById('liveAlertBtn');
    if (alertTrigger) {
        alertTrigger.addEventListener('click', () => {
            appendAlert('Nice, you triggered this alert message!', 'success');
        });
    }
});
