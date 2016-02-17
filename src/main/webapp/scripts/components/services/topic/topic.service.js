"use strict";

angular.module("forumApp")
    .factory("Topic", function ($resource) {
        return $resource("api/topics/:id", {}, {
            "query": { method: "GET", isArray: true },
            "get": {
                method: "GET",
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            "update": { method:"PUT" }
        });
    });
