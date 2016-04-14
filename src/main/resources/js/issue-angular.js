/**
 * Created by gustavo on 13/04/2016.
 */
var myApp = angular.module("issues", []);

myApp.factory("Issue", function () {
    return function (id, period, title, projectId, trackerId, assignedId, watchers, description) {
        return {
            id: id,
            period: period,
            title: title,
            projectId: projectId,
            assignedId: assignedId,
            watchersIds: watchers,
            description: description
        }
    }
})

myApp.controller("issueController", function ($scope, $http) {

    $scope.scheduledIssues = [];

    $scope.projectSelected = null;

    $scope.userAssignedSelected = null;

    $scope.watchesSelected = [];

    $scope.trackerSelected = null;

    $scope.projects = [];

    $scope.users = [];

    $scope.trackers = [];

    $scope.updateScheduledIssues = function () {
        $http.get('/issues/').then(function (data) {
            $scope.scheduledIssues = data.data;
        });
    }

    $scope.updateProjects = function () {
        $http.get('/projects/').then(function (data) {
            $scope.projects = data.data;
        });
    }

    $scope.updateUsers = function () {
        $scope.users = [];
        if ($scope.projectSelected != null) {
            var id = $scope.projectSelected.id;
            $http.get('projects/' + id + '/users/').then(function (data) {
                $scope.users = data.data;
            });
        }
    }

    $scope.updateTrackers = function () {
        $scope.trackers = [];
        if ($scope.projectSelected != null) {
            var id = $scope.projectSelected.id;
            $http.get('projects/' + id + '/trackers/').then(function (data) {
                $scope.trackers = data.data;
            });
        }
    }

    $scope.updateUsersAndTrackers = function () {
        $scope.updateUsers();
        $scope.updateTrackers();
    }

    $scope.removeIssue = function (issue) {
        $http.delete('issues/' + issue.id).success(function (data, status) {
            $scope.updateScheduledIssues();
        });
    }

    $scope.addIssue = function (issue) {
        $http.post('issues/', issue).success(function (data, status) {
            $scope.updateScheduledIssues();
        });
    }

});