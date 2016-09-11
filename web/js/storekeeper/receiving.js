$(function () {
    var pStorage = new ProductStorage();

    $.get("/dataAcceptor", function( data ) {
        var arr = data.data;
        var count = arr.length;
        var $table = $(".acceptor-table");
        for (var i = 0; i < count; i++){
            var $row = $("<div>");
            $row.addClass("acceptor-row");
            $row.attr("data-id",arr[i].id);

            var $name = $("<div>");
            $name.addClass("acceptor-name");
            $name.text(arr[i].fullName);
            $row.append($name);

            var $status = $("<div>");
            $status.addClass("acceptor-status");
            $row.append($status);

            if (arr[i].busy == true){
                $status.text("busy");
                $row.addClass("busy");
            }
            else{
                $status.text("free");
            }
            $table.append($row);
        }
        bind();
    });


    $(".delete-btn").on("click", function () {
        var attr = $(this).parent().parent().attr("data-storage-id");
        pStorage.remove(+attr);
        $(this).parent().parent().remove();
    });

    $(".edit-btn").on("click", function () {
        var attr = $(this).parent().parent().attr("data-storage-id");
        pStorage.toEdit(+attr);
        $(this).parent().parent().remove();
    });

    $(".add-btn").on("click", function () {
        try{
            pStorage.add();
            $(".message-block").text("");
        }
        catch (ex) {
            $(".message-block").text(ex);
        }
    });

    $(".create-btn").on("click",function(){
        var storage = pStorage.getStorage();
        var acceptors = $(".selected");
        var idArray = [];
        acceptors.each(function(){
            idArray.push(+$(this).attr("data-id"));
        })
    });
});



