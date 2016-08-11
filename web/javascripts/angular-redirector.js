app.controller('HomeRedirector', ['$http', '$location', 'userService', '$rootScope', '$scope',
    function ($http, $location, userService, $rootScope, $scope) {
        $("#wait-box").animate
        ({
            width: "40%",
            height: "40%",
            top: "30%",
            left: "30%"
        });
        userService.update();
        $("#exitButton").hide();
        $http.get("/userPosition").then
        (
            function (response) {
                if (response.data.position == "Администратор") {
                    $("#exitButton").show(250);
                    $("#wait-text").animate({
                        opacity: "0.0"
                    }, 500);
                    $("#wait-box").animate({
                        width: "100vw",
                        height: "90vh",
                        top: "10vh",
                        left: "0%",
                        backgroundColor: "#8DB6C8"
                    }, 500, function () {
                        $rootScope.$apply(function () {
                            $location.url("/admin");
                        });
                    });
                }
                else {
                    $location.url("/login");
                }
            },
            function (response) {
                $location.url("/login");
            }
        );
        $location.url("/wait");
    }]);
