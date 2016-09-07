function signIn(){
    var $button = $(".submit");
    $button.remove();
    var $message = $(".message");
    $message.text("Please, wait...");
    var loginData = $("#login").val();
    var passwordData =  $("#password").val();
    var hash = Sha256.hash(passwordData);
    var data = {login:loginData, password:hash};

    $.post("login", data,
        function (result) {
            if (result.answer == "true") {
                location.reload();
            }
            else {
                $message.text(result.answer);
                $(".signin-modal").append($button);
            }
        },
        "json"
    );
}


function signOut(){
    $.post("logout",
        function (result) {
            if (result.answer == "true") {
                location.reload();
            }
            else {
                alert(result.answer);
            }
        }
    );
}