/**
 * Created by konst on 8/24/2016.
 */
function login(){
    var loginData = $("#login").val();
    var passwordData =  $("#password").val();
    var hash = Sha256.hash(passwordData);
    var data = {login:loginData, password:hash};

    $.post("login", data,
        function (result) {
            if (result.answer === true) {
                location.replace("/admin");
            }
            else {
                alert(result.answer);
            }
        },
        "json"
    );
}