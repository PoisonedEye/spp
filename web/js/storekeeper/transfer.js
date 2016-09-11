$(function () {
    var aStorage = new AvailableProductStorage();
    aStorage.init();

    $(".move-btn").on("click", function () {
        var id = $(this).parent().parent().attr("data-id");
        var count = +$($(this).parent().children()[0]).val();
        if (isNaN(count)) {
            alert("Count must be a number")
        }
        aStorage.move(id, count);
    });

    $(".remove-btn").on("click", function () {
        var id = $(this).parent().parent().attr("data-id");
        aStorage.remove(id);
    });
})