app.controller('HeaderController', ['$http', '$scope', 'userService', '$location',
    function ($http, $scope, userService, $location) {
        $scope.logout = function () {
            $http.get("/logout").then
            (
                function (response) {
                    $location.url("/");
                },
                function (response) {
                }
            );
            $location.url("/wait");
        };
        $scope.user = userService.data;
    }]);

app.controller('FailedLoginController', ['$scope', '$location', 'failedLoginService',
    function ($scope, $location, failedLoginService) {
        $scope.ok = function () {
            $scope.data = {message: ""};
            $location.url("/");
        };
        failedLoginService.update();
        $scope.data = failedLoginService.data;
    }]);

app.controller('LoginController', ['$scope', '$http', 'userService', '$location',
    function ($scope, $http, userService, $location) {

        $scope.data = {login: "", password: ""};
        $scope.login = function () {
            var hash = Sha256.hash($scope.data.password);
            var request = "/login" + "?login=" + $scope.data.login + "&password=" + hash;
            $http.get(request).then
            (
                function (response) {
                    if (response.data.answer == true) {
                        $http.get("/userInfo").then
                        (
                            function (response) {
                                userService.data.name = response.data.fullName;
                                userService.data.position = response.data.position;
                            },
                            function (response) {
                            }
                        );
                        $location.url("/");
                    }
                    else {
                        $location.url("/failedLogin");
                    }
                },
                function (response) {
                }
            );
            $location.url("/wait");
        };

    }]);

app.controller('AdminController', ['$scope', '$http', 'entityService',
    function ($scope, $http, entityService) {
        $scope.edit = undefined;
        $(".nav-bar").animate({opacity: "1.0"}, 300);
        $scope.currentEntity = {};
        $scope.extraUndo = function(){};
        $scope.showEdit = function() {
            $($scope.edit).show().animate({opacity: "1.0"}, 300);
        };
        $scope.continue = function () {
            $scope.createFlag = false;
            $(".entity-answer").animate({opacity: "0.0"}, 300,
                function () {
                    $(".entity-answer").hide()
                });
            $($scope.edit).show().animate({opacity: "1.0"}, 300);
        };
        $scope.showAnswer = function (answer) {
            $scope.answer = answer;
            $($scope.edit).animate({opacity: "0.0"}, 300,
                function () {
                    $($scope.edit).hide();
                });
            $(".entity-answer").show().animate({opacity: "1.0"}, 300);
            $scope.showTable($scope.edit);
        };
        $scope.showTable = function (edit) {
            var lastEdit = $scope.edit;
            if (lastEdit != undefined) {
                $(lastEdit).animate({opacity: "0.0"}, 300,
                    function () {
                        $(lastEdit).hide();
                    });
            }
            $scope.edit = edit;
            $scope.entities = [];
            $scope.title = "Загрузка...";
            $(".right-bar").animate({opacity: "1.0"}, 300);
            $scope.currentEntity = {};
            switch (edit) {
                case '#employee-edit':{
                    $scope.extraUndo = function () {$scope.newPassword = ""};
                    $scope.title = "Сотрудники";
                    $scope.newPassword = undefined;

                    entityService.getEmployees().then(function(data){
                        $scope.entities = data.entities;
                        $scope.positions = data.positions;
                    },function(data){console.log(data);});
                    break;
                }
                case '#position-edit': {
                    $scope.title = "Должности";
                    entityService.getPositions().then(function(data){
                        $scope.entities = data.entities;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#address-edit': {
                    $scope.title = "Адреса";
                        entityService.getAddresses().then(function(data){
                        $scope.entities = data.entities;
                    },function(data){console.log(data)})
                    break;
                    }
                case '#acceptor-shift-edit': {
                    $scope.title = "Смены приемщиков";
                    entityService.getAcceptorShifts().then(function(data){
                    $scope.entities = data.entities;
                    $scope.employees = data.employees;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#cell-edit': {
                    $scope.title = "Ячейки";
                    entityService.getCells().then(function(data){
                        $scope.entities = data.entities;
                        $scope.cellTypes = data.cellTypes;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#cell-type-edit': {
                    $scope.title = "Виды ячеек";
                    entityService.getCellTypes().then(function(data){
                        $scope.entities = data.entities;
                        $scope.unitSets = data.unitSets;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#cell-visiting-edit': {
                    $scope.title = "Посещения ячеек";
                    entityService.getCellVisitings().then(function(data){
                        $scope.entities = data.entities;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#company-edit': {
                    $scope.title = "Компании";
                    entityService.getCompanies().then(function(data){
                        $scope.entities = data.entities;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#product-edit': {
                    $scope.title = "Товары";
                    entityService.getProducts().then(function(data){
                        $scope.entities = data.entities;
                        $scope.types = data.types;
                        $scope.recievings = data.recievings;
                        $scope.transfers = data.transfers;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#product-type-edit': {
                    $scope.title = "Виды товара";
                    entityService.getProductTypes().then(function(data){
                        $scope.entities = data.entities;
                        $scope.unitSets = data.unitSets;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#recieving-edit': {
                    $scope.title = "Приемки";
                    entityService.getRecievings().then(function(data){
                        $scope.entities = data.entities;
                        $scope.employees = data.employees;
                        $scope.companies = data.companies;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#transfer-edit': {
                    $scope.title = "Передачи";
                    entityService.getTransfers().then(function(data){
                        $scope.entities = data.entities;
                        $scope.employees = data.employees;
                        $scope.companies = data.companies;
                    },function(data){
                        console.log(data);
                    });
                    break;
                    }
                case '#unit-set-edit': {
                    $scope.title = "Системы измерений";
                    entityService.getUnitSets().then(function(data){
                        $scope.entities = data.entities;
                    },function(data){
                        console.log(data);
                    });
                    }
            }
        };
        $scope.click = function (index) {
            $scope.createFlag = false;
            $scope.index = index;
            $scope.continue();
            $(".selectedRow").removeClass("selectedRow");
            var row = $(".entity-table tr:eq(" + (index + 1) + ")");
            row.toggleClass("selectedRow");
            $scope.currentEntity = $.extend(true, {}, $scope.entities[index]);
        };
        $scope.undo = function () {
            if ($scope.createFlag !== true) {
                $scope.currentEntity = $.extend(true, {}, $scope.entities[$scope.index]);
                $scope.extraUndo();
            }
        };
        $scope.save = function () {
            if ($scope.createFlag === true)
                createEntity($scope.currentEntity).then(function(response) {
                    if (response.data)
                        $scope.showAnswer(response.data.answer);
                    else
                        console.log("error!!!");
                });
            else
                updateEntity($scope.currentEntity).then(function(response) {
                    if (response.data)
                        $scope.showAnswer(response.data.answer);
                    else
                        console.log("error!!!");
                });
        };
        $scope.delete = function () {
            deleteEntity($scope.currentEntity).then(function(response) {
                if (response.data)
                    $scope.showAnswer(response.data.answer);
                else
                    console.log("error!!!");
            });
        };

        //switch and case kingdom
        var deleteEntity = function(entity){
            switch ($scope.edit) {
                case '#employee-edit':
                    return entityService.deleteEmployee(entity);
                case '#acceptor-shift-edit':
                    return entityService.deleteAcceptorShift(entity);
                case '#address-edit':
                    return entityService.deleteAddress(entity);
                case '#cell-edit':
                    return entityService.deleteCell(entity);
                case '#cell-type-edit':
                    return entityService.deleteCellType(entity);
                case '#cell-visiting-edit':
                    return entityService.deleteCellVisiting(entity);
                case '#company-edit':
                    return entityService.deleteCompany(entity);
                case '#position-edit':
                    return entityService.deletePosition(entity);
                case '#product-edit':
                    return entityService.deleteProduct(entity);
                case '#product-type-edit':
                    return entityService.deleteProductType(entity);
                case '#recieving-edit':
                    return entityService.deleteRecieving(entity);
                case '#transfer-edit':
                    return entityService.deleteTransfer(entity);
                case '#unit-set-edit':
                    return entityService.deleteUnitSet(entity);
                }
            };
        var updateEntity = function(entity){
            switch ($scope.edit) {
                case '#acceptor-shift-edit':
                    return entityService.updateAcceptorShift(entity);
                case '#address-edit':
                    return entityService.updateAddress(entity);
                case '#cell-edit':
                    return entityService.updateCell(entity);
                case '#cell-type-edit':
                    return entityService.updateCellType(entity);
                case '#cell-visiting-edit':
                    return entityService.updateCellVisiting(entity);
                case '#company-edit':
                    return entityService.updateCompany(entity);
                case '#employee-edit':
                    return entityService.updateEmployee(entity,$scope.newPassword);
                case '#position-edit':
                    return entityService.updatePosition(entity);
                case '#product-edit':
                    return entityService.updateProduct(entity);
                case '#product-type-edit':
                    return entityService.updateProductType(entity);
                case '#recieving-edit':
                    return entityService.updateRecieving(entity);
                case '#transfer-edit':
                    return entityService.updateTransfer(entity);
                case '#unit-set-edit':
                    return entityService.updateUnitSet(entity);
            }
        };
        var createEntity = function(entity){
            switch ($scope.edit) {
                case '#acceptor-shift-edit':
                    return entityService.createAcceptorShift(entity);
                case '#address-edit':
                    return entityService.createAddress(entity);
                case '#cell-edit':
                    return entityService.createCell(entity);
                case '#cell-type-edit':
                    return entityService.createCellType(entity);
                case '#cell-visiting-edit':
                    return entityService.createCellVisiting(entity);
                case '#company-edit':
                    return entityService.createCompany(entity);
                case '#employee-edit':
                    return entityService.createEmployee(entity);
                case '#position-edit':
                    return entityService.createPosition(entity);
                case '#product-edit':
                    return entityService.createProduct(entity);
                case '#product-type-edit':
                    return entityService.createProductType(entity);
                case '#recieving-edit':
                    return entityService.createRecieving(entity);
                case '#transfer-edit':
                    return entityService.createTransfer(entity);
                case '#unit-set-edit':
                    return entityService.createUnitSet(entity);
            }
        };

        $scope.create = function () {
            $scope.continue();
            $(".selectedRow").removeClass("selectedRow");
            $scope.createFlag = true;
            $scope.currentEntity = {};
        };
    }]);

app.controller('WaitController', ['$scope', '$http', 'userService', '$location',
    function ($scope, $http, userService, $location) {

    }]);