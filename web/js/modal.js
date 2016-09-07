function message(message,title){
    if (title == undefined){
        title = "Message";
    }
    var $modal = $("#modal-message");
    if ($modal.length == 0){
        alert(message);
    }
    else{
        $modal.find(".message").text(message);
        $modal.find(".title").text(title);
        $(".hider").show(300);
        $modal.show(300);
    }
}

function dialog(message,title,button,callback){
    if (title == undefined){
        title = "Message";
    }
    if (message == undefined){
        message = "Text";
    }
    if (button == undefined){
        button = "Ok";
    }
    var $modal = $("#modal-dialog");
    if ($modal.length == 0){
        alert(message);
    }
    else{
        $modal.find(".message").text(message);
        $modal.find(".title").text(title);
        var $button = $modal.find(".submit");
        $button.text(button);
        $button.off();
        if (callback){
            $button.on("click",callback);
        }
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
