"use strict";

angular.module("forumApp")
    .config(function ($stateProvider) {
        $stateProvider
            .state("topic", {
                parent: "site",
                url: "/topic",
                data: {
                    pageTitle: "Topics"
                },
                views: {
                    "content@": {
                        templateUrl: "scripts/app/topic/topics.html",
                        controller: "TopicController"
                    }
                }
            })
            .state("topicDetail", {
                parent: "site",
                url: "/topic/:id",
                data: {
                    pageTitle: "Topic"
                },
                views: {
                    "content@": {
                        templateUrl: "scripts/app/topic/topic-detail.html",
                        controller: "TopicDetailController"
                    }
                }
            });
    });
