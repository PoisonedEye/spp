function toggleAcceptors() {
    $(".product-table").toggle(200);
    $(".acceptor-table").toggle(200);
}

$(function () {
    $(".acceptor-row").on("click", function () {
        var $this = $(this);
        if (!$this.hasClass("busy")) {
            if ($this.hasClass("selected")) {
                $this.removeClass("selected");
                $($this.children()[1]).text("free");
            }
            else {
                $this.addClass("selected");
                $($this.children()[1]).text("selected");
            }
        }
    });

});

