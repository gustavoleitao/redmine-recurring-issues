/**
 * Created by gustavo on 13/04/2016.
 */
var myApp = angular.module("issues", []);

myApp.controller("issueController", function ($scope, $http, redmineService, issueService, Issue) {

    $scope.issue = new Issue();

    $scope.scheduledIssues = [];

    $scope.projects = [];

    $scope.users = [];

    $scope.trackers = [];

    $scope.updateProjects = function () {
        redmineService.getProjects(function (data) {
            $scope.projects = data.data;
        });
    }

    $scope.getUsersByProject = function () {
        $scope.users = [];
        if ($scope.issue.project != null) {
            var id = $scope.issue.project.id;
            redmineService.getUsersByProject(id, function (data) {
                $scope.users = data.data;
            });
        }
    };

    $scope.updateTrackers = function () {
        $scope.trackers = [];
        if ($scope.issue.project!= null) {
            var id = $scope.issue.project.id;
            redmineService.getTrackerByProject(id, function (data) {
                $scope.trackers = data.data;
            })
        }
    };

    $scope.updateUsersAndTrackers = function () {
        $scope.getUsersByProject();
        $scope.updateTrackers();
    };

    $scope.updateScheduledIssues = function () {
        issueService.all(function (data) {
            $scope.scheduledIssues = data.data;
        })
    };

    $scope.removeIssue = function (issue) {
        issueService.remove(issue, function (data) {
            $scope.updateScheduledIssues();
        })
    };

    $scope.addIssue = function (issue) {
        issueService.add(issue, function (data) {
            $scope.updateScheduledIssues();
            $scope.clearIssue();
            $scope.updateUsersAndTrackers();
        });
    };

    $scope.editIssue = function (issue) {
        $scope.issue = clone(issue);
    };

    $scope.clearIssue = function(){
        $scope.issue = new Issue();
        $scope.users = [];
        $scope.trackers = [];
    }

    function clone(obj) {
        if (null == obj || "object" != typeof obj) return obj;
        var copy = obj.constructor();
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
        }
        return copy;
    }

});
