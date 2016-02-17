"use strict";

angular.module("forumApp", ["ui.router", "ngResource"])
    .run(function ($rootScope, $location, $window, $http, $state) {
        $rootScope.$on("$stateChangeSuccess",  function(event, toState, toParams, fromState, fromParams) {
            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;
        });

        $rootScope.back = function() {
            if ($rootScope.previousStateName === "activate" || $state.get($rootScope.previousStateName) === null) {
                $state.go("topic");
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };
    })
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            'abstract': true,
            views: {
                'navbar@': {
                    templateUrl: 'scripts/components/navbar/navbar.html',
                    controller: 'NavbarController'
                }
            }
        });
    });
