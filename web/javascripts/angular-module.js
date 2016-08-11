var app = angular.module('app', ['ngRoute']);

app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
    // маршрут для страницы home
        .when('/', {
            templateUrl: '/wait',
            controller: 'HomeRedirector'
        })
        .when('/failedLogin', {
            templateUrl: '/failedLogin',
            controller: 'FailedLoginController'
        })
        .when('/admin', {
            templateUrl: '/admin',
            controller: 'AdminController'
        })
        .when('/login', {
            templateUrl: '/entry',
            controller: 'LoginController'
        })
        .when('/wait', {
            templateUrl: '/wait',
            controller: 'WaitController'
        });
    $locationProvider.html5Mode(false);
});
