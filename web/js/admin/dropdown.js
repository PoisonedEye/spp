$(function(){
    var $table = $("#admin-table");
    var currentTable = "Employee";
    var template = {};
    var addCounter = 0;
    var deletions = [];

    $(".dropdown-content *").on("click",function(){
        var tableName = $(this).attr("data-table");
        currentTable = tableName;
        loadTable(tableName);
    });

    function loadTable(tableName){
        $.get("/data"+tableName, function( data ) {
            var arr = data.data;
            var count = arr.length;
            if (count > 0){
                $table.children().remove();
                var $thead = $("<thead>");
                $thead.append("<tr>");
                $table.append($thead);
                var first = arr[0];
                template = first;
                var $head = $(".admin-table tr");
                $head.append($("<th>").text("id"));
                for (var field in first){
                    if (field != "id"){
                        var head = $("<th>");
                        head.text(field);
                        $head.append(head);
                    }
                }
                var $tbody = $("<tbody>");
                $table.append($tbody);
                arr.forEach(function (entity){
                    var row = $("<tr>");
                    row.append($("<td>").text(entity.id));
                    for (var field in entity){
                        if (field != "id"){
                            var cell = $("<td>");
                            var value = entity[field];
                            if (value !== null && value.id !== undefined){
                                value = value.id;
                            }
                            cell.text(value);
                            row.append(cell);
                        }
                    }
                    var $dltBtn = $("<button>");
                    $dltBtn.text("Delete");
                    $dltBtn.addClass("btn");
                    row.append($("<td>").append($dltBtn));
                    $tbody.append(row);
                });
                bind();
            }
            else{
                alert("This table is empty");
            }
        });
    }

    loadTable("Employee");

    function bind(){
        //input when click
        $("td").on("click", function(){
            var $this = $(this);
            if ($this.attr("data-clicked") != "true"){
                var first = $this.parent().find("td:first-child");
                var last = $this.parent().find("td:last-child");
                if (first[0] != $this[0] && last[0] != $this[0]){
                    var value = $this.text();
                    $this.text("");
                    $this.attr("data-clicked","true");
                    $this.parent().attr("data-changed","true");
                    var input = $("<input>");
                    input.val(value);
                    $this.append(input);
                }
            }
        });
        $(".btn").on("click",function(){
            var $this = $(this);
            var row = $this.parent().parent();
            var id = $(row.children()[0]).text();
            var message = "Delete the entity with id" + id + "? You can abort deleting by pressing 'Cancel' button.";

            dialog(message,"Delete","Delete",function(){
                deletions.push(id);
                row.remove();
                modalHide();
            })

        });
    }

    $("#cancel-btn").on("click", function(){
        loadTable(currentTable);
    });

    $("#save-btn").on("click", function(){
        var rows = $("tr[data-changed='true']");
        //save names of table fields
        var propertyArr = [];
        propertyArr[0] = "id";
        i = 1;
        for (var field in template){
            if (field != "id"){
                propertyArr[i++] = field;
            }
        }
        var count = rows.length + deletions.length;
        var counter = 0;
        var resultMessage = "";

        for (var i = 0; i < deletions.length; i++){
            function a() {
                var id = deletions[i];
                $.ajax({
                    url: "delete" + currentTable,
                    data: "{\"data\":" + JSON.stringify({id: id}) + "}",
                    dataType: 'json',
                    contentType: 'application/json',
                    type: 'POST',
                    async: true,
                    success: function (result) {
                        resultMessage = resultMessage + ("Id " + id + ": " + result.answer + "\r\n");
                        counter++;
                        if (count == counter) {
                            message(resultMessage, "Save");
                            loadTable(currentTable);
                        }
                    },
                    error: function () {
                        resultMessage += "Id " + id + ": Can't delete, unknown error.\r\n";
                        counter++;
                        if (count == counter){
                            message(resultMessage, "Save");
                            loadTable(currentTable);
                        }
                    }
                });
            }
            a();
        }
        deletions = [];
        rows.each(function(){
            var $this = $(this);
            var entity = {};
            var children = $this.children();
            for (var i = 0; i < children.length; i++){
                var child =  $(children[i]);
                if (child.attr("data-clicked") == "true"){
                    if (propertyArr[i] != "password"){
                        entity[propertyArr[i]] = child.children().val();
                    }
                    else{
                        entity[propertyArr[i]] = Sha256.hash(child.children().val());
                    }

                }
                else{
                    if (propertyArr[i] != "password"){
                        entity[propertyArr[i]] = child.text();
                    }
                }
            }

            if ($this.attr("data-added") == "true"){
                var method = "create";
            }
            else{
                method = "update";
            }
            $.ajax({
                url: method  + currentTable,
                data: "{\"data\":" + JSON.stringify(entity) + "}",
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                async: true,
                success: function (result) {
                    resultMessage = resultMessage + ("Id " + entity.id + ": " + result.answer + "\r\n");
                    counter++;
                    if (count == counter){
                        message(resultMessage, "Update");
                        loadTable(currentTable);
                    }
                },
                error:function(){
                    resultMessage += "Id " + entity.id + ":" + "Can't " + method + ", unknown error.\r\n";
                    counter++;
                    if (count == counter){
                        message(resultMessage, "Update");
                        loadTable(currentTable);
                    }
                }
            });
        })
    });

    $("#add-btn").on("click", function(){
        var $row = $("<tr>");
        $row.attr("data-added","true");
        $row.attr("data-changed","true");
        $row.append($("<td>").text("N" + addCounter++));
        for (var field in template){
            if (field != "id"){
                var $cell = $("<td>");
                $cell.attr("data-clicked","true");
                var $input = $("<input>");
                $cell.append($input);
                $row.append($cell);
            }
        }
        $table.find("tbody").prepend($row);
    });
});





