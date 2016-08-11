app.factory('userService', ["$http", function ($http) {
    var data = {data: {name: "", position: ""}, update: {}};
    data.update = function () {
        $http.get("/userInfo").then
        (
            function (response) {
                data.data.name = response.data.fullName;
                data.data.position = response.data.position;
            },
            function (response) {
            }
        );
    };
    data.update();
    return data;
}]);

app.factory('entityService',["$http","$q", function ($http,$q) {
    var jsonRequest = function(url, data) {
        var config = {
            headers: {
                dataType: 'json',
                contentType: "application/json;charset=utf-8"
            }
        };
        data = "{\"data\":" + JSON.stringify(data) + "}";
        return $http.post(url, data, config);
    };
    var data =
    {
        getEmployees:function(){
            var result = $q.defer();
            $http.get("/dataEmployees").then(
                function (response) {
                    if (response.data){
                        var entities = response.data.data;
                        entities.forEach(function (employee, index, array) {
                            employee._name = employee.fullName;
                        });
                        $http.get("/dataPositions").then(
                            function (response) {
                                if (response.data) {
                                    var positions = response.data.data;
                                    entities.forEach(function (employee, index, array) {
                                        positions.forEach(function (position, index, array) {
                                            if (employee.position.name == position.name) {
                                                employee.position = position;
                                            }
                                        });
                                    });
                                    result.resolve({entities:entities,positions:positions});
                                }
                                else
                                    result.reject("error");
                            },
                            function (response) {
                                result.reject("error");
                            }
                        );
                    }
                    else
                        result.reject("error");
                },
                function (response) {
                    result.reject("error");
                }
            );
            return result.promise;
        },
        getAddresses:function(){
            var result = $q.defer();
            $http.get("/dataAddresses").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (address, index, array) {
                            address._name = "г. " + address.city + " ул. " + address.street;
                        });
                        result.resolve({entities: entities});
                    }
                    else
                        result.reject("error");
                },
                function (response) {result.reject("error");}
                );
            return result.promise;
        },
        getAcceptorShifts:function(){
            var result = $q.defer();
            $http.get("/dataAcceptorShifts").then(
                function (response) {
                    if (response.data) {
                      var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.begining;
                        });
                        $http.get("/dataEmployees").then(
                            function (response) {
                                if (response.data) {
                                    var employees = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        employees.forEach(function (employee, index, array) {
                                            if (entity.acceptor.id == employee.id) {
                                                entity.acceptor = employee;
                                            }
                                        });
                                    });
                                    result.resolve({entities:entities,employees:employees});
                                }
                                else {
                                    result.reject("error");
                                }
                            },
                            function (response) {
                                result.reject("error");
                            }
                        );
                    }
                    else
                        result.reject("error");
                },
                function (response) {result.reject("error");}
            );
            return result.promise;
        },
        getCells:function(){
            var result = $q.defer();
            $http.get("/dataCells").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.id + " " + entity.number;
                        });

                        $http.get("/dataCellTypes").then(
                            function (response) {
                                if (response.data) {
                                    var cellTypes = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        cellTypes.forEach(function (type, index, array) {
                                            if (entity.cellType.id == type.id) {
                                                entity.cellType = type;
                                            }
                                        });
                                    });
                                    result.resolve({entities: entities, cellTypes: cellTypes});
                                }
                                else {
                                    result.reject("error");
                                }
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                    }
                    else
                        result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getCellTypes:function(){
            var result = $q.defer();
            $http.get("/dataCellTypes").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = "Вид " + entity.id;
                        });
                        $http.get("/dataUnitSets").then(
                            function (response) {
                                if (response.data) {
                                    var unitSets = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        unitSets.forEach(function (set, index, array) {
                                            if (entity.unitSet.id == set.id) {
                                                entity.unitSet = set;
                                            }
                                        });
                                    });
                                    result.resolve({entities: entities, untSets: unitSets});
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getCellVisitings:function(){
            var result = $q.defer();
            $http.get("/dataCellVisitings").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.time;
                            entity.shift = entity.shift.id;
                            entity.product = entity.product.id;
                            entity.cell = entity.cell.id;
                        });
                        result.resolve({entities: entities});
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getCompanies:function(){
            var result = $q.defer();
            $http.get("/dataCompanies").then(
                function (response) {
                    if(response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.name;
                        });
                        result.resolve({entities: entities});
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getPositions:function(){
            var result = $q.defer();
            $http.get("/dataPositions").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.splice(0, 1);
                        entities.forEach(function (position, index, array) {
                            position._name = position.name;
                        });
                        result.resolve({entities: entities});
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getProducts:function(){
            var result = $q.defer();
            $http.get("/dataProducts").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.productType.fullModelName;
                        });
                        var types = [];
                        var recievings = [];
                        var transfers = [];
                        var req1 = $http.get("/dataProductTypes").then(
                            function (response) {
                                if (response.data) {
                                    types = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        types.forEach(function (type, index, array) {
                                            if (entity.productType.id == type.id) {
                                                entity.productType = type;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        var req2 = $http.get("/dataRecievings").then(
                            function (response) {
                                if (response.data) {
                                    recievings = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        recievings.forEach(function (recieving, index, array) {
                                            if (entity.recieving.id == recieving.id) {
                                                entity.recieving = recieving;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        var req3 = $http.get("/dataTransfers").then(
                            function (response) {
                                if (response.data) {
                                    transfers = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        transfers.forEach(function (transfer, index, array) {
                                            if (entity.transfer.id == transfer.id) {
                                                entity.transfer = transfer;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        $q.all([req1, req2, req3]).then(function () {
                            result.resolve(
                                {
                                    entities: entities,
                                    types: types,
                                    recievings: recievings,
                                    transfers: transfers
                                });
                        })
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getProductTypes:function(){
            var result = $q.defer();
            $http.get("/dataProductTypes").then(
                function (response) {
                    var entities = response.data.data;
                    entities.forEach(function (entity, index, array) {
                        entity._name = entity.fullModelName;
                    });
                    $http.get("/dataUnitSets").then(
                        function (response) {
                            if (response.data) {
                                var unitSets = response.data.data;
                                entities.forEach(function (entity, index, array) {
                                    unitSets.forEach(function (set, index, array) {
                                        if (entity.unitSet.id == set.id) {
                                            entity.unitSet = set;
                                        }
                                    });
                                });
                                result.resolve({entities: entities, unitSets: unitSets});
                            }
                            else result.reject("error");
                        },
                        function (response) {result.reject("error")}
                    );
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getRecievings:function(){
            var result = $q.defer();
            $http.get("/dataRecievings").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.time;
                        });
                        var employees = [];
                        var companies = [];
                        var req1 = $http.get("/dataEmployees").then(
                            function (response) {
                                if (response.data) {
                                    employees = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        employees.forEach(function (employee, index, array) {
                                            if (entity.reciever.id == employee.id) {
                                                entity.reciever = employee;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        var req2 = $http.get("/dataCompanies").then(
                            function (response) {
                                if (response.data) {
                                    companies = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        companies.forEach(function (company, index, array) {
                                            if (entity.company.id == company.id) {
                                                entity.company = company;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        $q.all([req1, req2]).then(function () {
                            result.resolve(
                                {
                                    entities: entities,
                                    employees: employees,
                                    companies: companies,
                                });
                        })
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getTransfers:function(){
            var result = $q.defer();
            $http.get("/dataTransfers").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = entity.time;
                        });
                        var employees = [];
                        var companies = [];
                        var req1 = $http.get("/dataEmployees").then(
                            function (response) {
                                if (response.data) {
                                    employees = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        employees.forEach(function (employee, index, array) {
                                            if (entity.transferer.id == employee.id) {
                                                entity.transferer = employee;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        var req2 = $http.get("/dataCompanies").then(
                            function (response) {
                                if (response.data) {
                                    companies = response.data.data;
                                    entities.forEach(function (entity, index, array) {
                                        companies.forEach(function (company, index, array) {
                                            if (entity.company.id == company.id) {
                                                entity.company = company;
                                            }
                                        });
                                    });
                                }
                                else result.reject("error");
                            },
                            function (response) {
                                result.reject("error")
                            }
                        );
                        $q.all([req1, req2]).then(function () {
                            result.resolve(
                                {
                                    entities: entities,
                                    employees: employees,
                                    companies: companies,
                                });
                        })
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },
        getUnitSets:function(){
            var result = $q.defer();
            $http.get("/dataUnitSets").then(
                function (response) {
                    if (response.data) {
                        var entities = response.data.data;
                        entities.forEach(function (entity, index, array) {
                            entity._name = "Система " + entity.id;
                        });
                        result.resolve({entities: entities});
                    }
                    else result.reject("error");
                },
                function (response) {result.reject("error")}
            );
            return result.promise;
        },

        deleteEmployee:function(entity){
            entity.position = entity.position.id;
            return jsonRequest("/deleteEmployee",entity);
        },
        deleteAddress:function(entity){
            entity.employee = entity.employee.id;
            return jsonRequest("/deleteAddress",entity);

        },
        deleteAcceptorShift:function(entity){
            entity.acceptor = entity.acceptor.id;
            return jsonRequest("/deleteAcceptorShift",entity);
        },
        deleteCell:function(entity){
            entity.cellType = entity.cellType.id;
            return jsonRequest("/deleteCell",entity);
        },
        deleteCellType:function(entity){
            entity.unitSet = entity.unitSet.id;
            return jsonRequest("/deleteCellType",entity);
        },
        deleteCellVisiting:function(entity){
            return jsonRequest("/deleteCellVisiting",entity);
        },
        deleteCompany:function(entity){
            return jsonRequest("/deleteCompany",entity);
        },
        deletePosition:function(entity){
            return jsonRequest("/deletePosition",entity);
        },
        deleteProduct:function(entity){
            entity.recieving = entity.recieving.id;
            entity.transfer = entity.transfer.id;
            entity.productType = entity.productType.id;
            return jsonRequest("/deleteProduct",entity);
        },
        deleteProductType:function(entity){
            entity.unitSet = entity.unitSet.id;
            return jsonRequest("/deleteProductType",entity);
        },
        deleteRecieving:function(entity){
            entity.reciever = entity.reciever.id;
            entity.company = entity.company.id;
            return jsonRequest("/deleteRecieving",entity);
        },
        deleteTransfer:function(entity){
            entity.transferer = entity.transferer.id;
            entity.company = entity.company.id;
            return jsonRequest("/deleteTransfer",entity);
        },
        deleteUnitSet:function(entity){
            return jsonRequest("/deleteUnitSet",entity);
        },

        createEmployee:function(entity){
            if (entity.position != undefined)
                entity.position = entity.position.id;
            if ($scope.newPassword != undefined && $scope.newPassword != "")
                entity.newPassword = Sha256.hash($scope.newPassword);
            return jsonRequest("/createEmployee", entity);
        },
        createAddress:function(entity){
            if (entity.employee != undefined)
                entity.employee = entity.employee.id;
            return jsonRequest("/createAddress", entity);
        },
        createAcceptorShift:function(entity){
            if (entity.acceptor != undefined)
                entity.acceptor = entity.acceptor.id;
            return jsonRequest("/createAcceptorShift", entity);
        },
        createCell:function(entity){
            if (entity.cellType != undefined)
                entity.cellType = entity.cellType.id;
            return jsonRequest("/createCell", entity);
        },
        createCellType:function(entity){
            if (entity.unitSet != undefined)
                entity.unitSet = entity.unitSet.id;
            return jsonRequest("/createCellType", entity);
        },
        createCellVisiting:function(entity){
            return jsonRequest("/createCellVisiting", entity);
        },
        createCompany:function(entity){
            return jsonRequest("/createCompany", entity);
        },
        createPosition:function(entity){
            return jsonRequest("/createPosition", entity);
        },
        createProduct:function(entity){
            if (entity.productType != undefined)
                entity.productType = entity.productType.id;
            if (entity.recieving != undefined)
                entity.recieving = entity.recieving.id;
            if (entity.transfer != undefined)
                entity.transfer = entity.transfer.id;
            return jsonRequest("/createProduct", entity);
        },
        createProductType:function(entity){
            if (entity.unitSet != undefined)
                entity.unitSet = entity.unitSet.id;
            return jsonRequest("/createProductType", entity);
        },
        createRecieving:function(entity){
            if (entity.reciever != undefined)
                entity.reciever = entity.reciever.id;
            if (entity.company != undefined)
                entity.company = entity.company.id;
            return jsonRequest("/createRecieving", entity);
        },
        createTransfer:function(entity){
            if (entity.transferer != undefined)
                entity.transferer = entity.transferer.id;
            if (entity.company != undefined)
                entity.company = entity.company.id;
            return jsonRequest("/createTransfer", entity);
        },
        createUnitSet:function(entity){
            return jsonRequest("/createUnitSet", entity);
        },

        updateEmployee:function(entity,newPassword){
            if (newPassword != undefined && newPassword != "")
                entity.newPassword = Sha256.hash(newPassword);
            return jsonRequest("/updateEmployee",entity);
        },
        updateAddress:function(entity){
            entity.employee = entity.employee.id;
            return jsonRequest("/updateAddress",entity);
        },
        updateAcceptorShift:function(entity){
            entity.acceptor = entity.acceptor.id;
            return jsonRequest("/updateAcceptorShift",entity);
        },
        updateCell:function(entity){
            entity.cellType = entity.cellType.id;
            return jsonRequest("/updateCell",entity);
        },
        updateCellType:function(entity){
            entity.unitSet = entity.unitSet.id;
            return jsonRequest("/updateCellType",entity);
        },
        updateCellVisiting:function(entity){
            return jsonRequest("/updateCellVisiting",entity);
        },
        updateCompany:function(entity){
            return jsonRequest("/updateCompany",entity);
        },
        updatePosition:function(entity){
            return jsonRequest("/updatePosition",entity);
        },
        updateProduct:function(entity){
            entity.productType = entity.productType.id;
            entity.recieving = entity.recieving.id;
            entity.transfer = entity.transfer.id;
            return jsonRequest("/updateProduct",entity);
        },
        updateProductType:function(entity){
            entity.unitSet = entity.unitSet.id;
            return jsonRequest("/updateProductType",entity);
        },
        updateRecieving:function(entity){
            entity.reciever = entity.reciever.id;
            entity.company = entity.company.id;
            return jsonRequest("/updateRecieving",entity);
        },
        updateTransfer:function(entity){
            entity.transferer = entity.transferer.id;
            entity.company = entity.company.id;
            return jsonRequest("/updateTransfer",entity);
        },
        updateUnitSet:function(entity){
            return jsonRequest("/updateUnitSet",entity);
        }
    };
    return data;
}]);

app.factory('failedLoginService', ["$http", function ($http) {
    var data = {data: {message: "lol"}, update: {}};
    data.update = function () {
        $http.get("/failedLoginInfo").then
        (
            function (response) {
                data.data.message = response.data.failedLoginMessage;
            },
            function (response) {
            }
        );
    }
    data.update();
    return data;
}]);