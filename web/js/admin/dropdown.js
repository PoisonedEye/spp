$(function(){
    var $table = $(".admin-table");

    $(".dropdown-content *").on("click",function(){
        var tableName = $(this).attr("data-table");
        loadTable(tableName);
    });

    function loadTable(tableName){
        $.get("/data"+tableName, function( data ) {
            var arr = data.data;
            var count = arr.length;
            if (count > 0){
                $table.children().remove();
                $table.append("<tr>");
                var first = arr[0];
                var $head = $(".admin-table tr");
                $head.append($("<th>").text("id"));
                for (var field in first){
                    if (field != "id"){
                        var head = $("<th>");
                        head.text(field);
                        $head.append(head);
                    }
                }

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
                    $table.append(row);
                });
            }
            else{
                alert("This table is empty");
            }
        });
    }

    loadTable("Employees");
});



