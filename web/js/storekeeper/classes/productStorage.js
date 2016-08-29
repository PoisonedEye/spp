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