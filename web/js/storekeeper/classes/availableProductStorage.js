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
                row = $(".product-selected-table .product-row[data-id='"+id+"']");
            }
            if (count <= available[id].count - moved[id]) {
                moved[id] += count;
                $(row.children()[2]).text(moved[id]);
                if (available[id].count == moved[id]) {
                    $(".product-available-table .product-row[data-id='"+id+"']").remove();
                }
                else {
                    $($(".product-available-table .product-row[data-id='"+id+"']")
                        .children()[2]).text(available[id].count - moved[id]);
                }
            }
        }
    }

    this.remove = function (id) {
        var row = $(".product-selected-table .product-row[data-id='"+id+"']");
        var count = $(row.children()[2]).text();
        var aRow = $(".product-available-table .product-row[data-id='"+id+"']");
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