function message(message,title){
    if (title == undefined){
        title = "Message";
    }
    var $modal = $("#modal-message");
    if ($modal.length == 0){
        alert(message);
    }
    else{
        $("#modal-message .message").text(message);
        $("#modal-message .title").text(title);
        $(".hider").show(300);
        $modal.show(300);
    }
}

function modalShow() {
    $(".hider").show(300);
    $(".modal").show(300);
}

function modalHide() {
    $(".hider").hide(300);
    $(".modal").hide(300);
}
