function toggleAcceptors() {
    $(".product-zone").hide(200);
    $(".acceptor-table").toggle(200);
}

function toggleProducts() {
    $(".acceptor-table").hide(200);
    $(".product-zone").toggle(200);
    
}

function collapseAvailable() {
    $(".product-available-table").toggle(200);
}
function collapseSelected() {
    $(".product-selected-table").toggle(200);
}

$(function () {
    var pStorage = new productStorage();
    var aStorage = new availableProductStorage();
    aStorage.init();

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
});

function productStorage() {
    var counter = 0;
    var storage = [];
    var existStorage = [];
    this.add = function () {
        var note = {};
        //name
        note.name = $("#full-model-name-edit").val();
        if (note.name == "") {
            throw "Product name is empty."
        }
        storage.forEach(function (item, i, arr) {
            if (item.name == note.name) {
                throw "Product with this name already exist."
            }
        })
        existStorage.forEach(function (item, i, arr) {
            if (item.name == note.name) {
                throw "Product with this name already exist in database."
            }
        })
        //barcode
        note.barcode = $("#barcode-edit").val();
        if (note.barcode == "") {
            throw "Product barcode is empty."
        }
        storage.forEach(function (item, i, arr) {
            if (item.barcode == note.barcode) {
                throw "Product with this barcode already exist."
            }
        })
        existStorage.forEach(function (item, i, arr) {
            if (item.barcode == note.barcode) {
                throw "Product with this barcode already exist in database."
            }
        })

        note.producer = $("#producer-edit").val();
        if (note.producer == "") {
            throw "Product producer is empty."
        }
        note.model = $("#model-edit").val();
        if (note.model == "") {
            throw "Product model is empty."
        }

        note.weight = $("#weight-edit").val();
        if (note.weight <= 0) {
            throw "Product weight must be greater than zero."
        }
        if (isNaN(+note.weight)) {
            throw "Weight must be a number";
        }

        note.width = $("#pack-width-edit").val();
        if (note.width <= 0) {
            throw "Product width must be greater than zero."
        }
        if (isNaN(+note.width)) {
            throw "Width must be a number";
        }

        note.height = $("#pack-height-edit").val();
        if (note.height <= 0) {
            throw "Product height must be greater than zero."
        }
        if (isNaN(+note.height)) {
            throw "Height must be a number";
        }

        note.length = $("#pack-length-edit").val();
        if (note.length <= 0) {
            throw "Product length must be greater than zero."
        }
        if (isNaN(+note.length)) {
            throw "Length must be a number";
        }

        note.count = $("#count-edit").val();
        if (note.count <= 0) {
            throw "Product count must be greater than zero."
        }
        if (isNaN(+note.count)) {
            throw "Count must be a number";
        }
        
        note.storageId = counter++;
        storage[note.storageId] = note;

        var row = $(".product-row.for-copy").clone(true);
        row.removeClass("for-copy");
        $(row.children()[0]).text(note.name);
        $(row.children()[1]).text(note.barcode);
        $(row.children()[2]).text(note.count);
        row.attr("data-storage-id", note.storageId);
        
        $(".product-table").append(row);
        return note.storageId;
    }

    this.remove = function (storageId) {
        delete storage[storageId];
    }

    this.toEdit = function (storageId) {
        var note = storage[storageId];
        $("#full-model-name-edit").val(note.name);
        $("#barcode-edit").val(note.barcode);       
        $("#producer-edit").val(note.producer);
        $("#model-edit").val(note.model);
        $("#weight-edit").val(note.weight);
        $("#pack-width-edit").val(note.width);
        $("#pack-height-edit").val(note.height);
        $("#pack-length-edit").val(note.length);
        $("#count-edit").val(note.count);
        delete storage[storageId];
    }
}

function availableProductStorage() {
    var available = [];
    var moved = [];
    var idCounter = 1;

    this.move = function (id, count) {
        if (count > 0) {
            if (!moved[id]) {               
                moved[id] = 0;
                if (count <= available[id].count) {
                    var row = $(".product-selected-table .product-row.for-copy").clone(true);
                    row.removeClass("for-copy");
                    row.attr("data-id", id);
                    $(row.children()[0]).text(available[id].name);
                    $(row.children()[1]).text(available[id].barcode);
                    $(".product-selected-table").append(row);
                }
            }
            else {
                var row = $(`.product-selected-table .product-row[data-id='${id}']`);
            }
            if (count <= available[id].count - moved[id]) {
                moved[id] += count;
                $(row.children()[2]).text(moved[id]);
                if (available[id].count == moved[id]) {
                    $(`.product-available-table .product-row[data-id='${id}']`).remove();
                }
                else {
                    $($(`.product-available-table .product-row[data-id='${id}']`)
                    .children()[2]).text(available[id].count - moved[id]);
                }              
            }          
        }      
    }

    this.remove = function (id) {         
        var row = $(`.product-selected-table .product-row[data-id='${id}']`);
        var count = $(row.children()[2]).text();
        var aRow = $(`.product-available-table .product-row[data-id='${id}']`);
        if (aRow.length == 0) {
            aRow = $(".product-available-table .product-row.for-copy").clone(true);
            aRow.removeClass("for-copy");
            $(aRow.children()[0]).text($(row.children()[0]).text());
            $(aRow.children()[1]).text($(row.children()[1]).text());
            aRow.attr("data-id", id);
            $(".product-available-table").append(aRow);
        }
        $(aRow.children()[2]).text(available[id].count);
        delete moved[id];
        row.remove();      
    }

    this.init = function () {
        var n1 = {
            databaseId: 1,
            id: idCounter++,
            name: "model1",
            barcode: 1,
            count: 5
        }
        var n2 = {
            databaseId: 2,
            id: idCounter++,
            name: "model2",
            barcode: 2,
            count: 5
        }
        available[n1.id] = n1;
        available[n2.id] = n2;

        available.forEach(function (item, i, arr) {
            var row = $(".product-available-table .product-row.for-copy").clone(true);
            row.removeClass("for-copy");
            $(row.children()[0]).text(item.name);
            $(row.children()[1]).text(item.barcode);
            $(row.children()[2]).text(item.count);
            $($(row.children()[3]).children()[0]).val(0);
            row.attr("data-id", i);

            $(".product-available-table").append(row);

        });
    }
}



