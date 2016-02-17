"use strict";

angular.module("forumApp")
    .controller("TopicDetailController", function ($scope, $stateParams, Topic) {
        $scope.topic = {};
        $scope.reply = {};
        $scope.load = function (id) {
            Topic.get({id: id}, function(result) {
              $scope.topic = result;
            });
        };
        $scope.load($stateParams.id);
        
        $scope.update = function (id) {
    	    Topic.get({id: id}, function(result) {
    	        $scope.topic = result;
    	        $("#saveTopicModal").modal("show");
    	    });
    	};
    	
    	$scope.createReply = function () {
        	$scope.reply.source = "App";
        	$scope.reply.title = "RE:" + $scope.topic.title;
        	$scope.reply.parentId = $scope.topic.id;
        	$scope.reply.registration = null;
        	
	    	Topic.update($scope.reply, function () {
	        	$("#saveTopicModal").modal("hide");
	        	Topic.get({id: $scope.topic.id}, function(result) {
	      	       $scope.topic = result;
	      	       $scope.reply = {};
	        	 });
	        });
        };
        
        $scope.clear = function () {
            $scope.topic = {id: null, title:null, userName: null, content: null, registration: null};
            $scope.reply = {id: null, title:null, userName: null, content: null, registration: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });