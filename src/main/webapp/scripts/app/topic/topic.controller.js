"use strict";

angular.module("forumApp")
    .controller("TopicController", function ($scope, Topic) {
    	$scope.topics = [];
    	$scope.sortType = "id";
    	$scope.sortReverse = false;
    	$scope.searchTitle = '';
    	$scope.searchUserName = ''; 
        
        $scope.loadAll = function() {
            Topic.query(function(result) {
               $scope.topics = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
        	$scope.topic.source = "App";
            Topic.update($scope.topic,
                function () {
                    $scope.loadAll();
                    $("#saveTopicModal").modal("hide");
                    $scope.clear();
                });
        };
        
        $scope.clear = function () {
            $scope.topic = {id: null, title:null, userName: null, content: null, registration: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });