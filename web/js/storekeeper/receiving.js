$(function () {
    var pStorage = new productStorage();
          
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
});



