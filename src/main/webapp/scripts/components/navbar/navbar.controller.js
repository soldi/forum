'use strict';

angular.module('forumApp')
    .controller('NavbarController', function ($scope, $location, $state) {
        $scope.$state = $state;
    });
