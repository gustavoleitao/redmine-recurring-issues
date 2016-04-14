/**
 * Created by gustavo on 13/04/2016.
 */
var myApp = angular.module("issues", []);

myApp.controller("issueController", function ($scope, $http) {

    $scope.scheduledIssues = [
        {
            period: "* * * * ",
            project: {id: 0, name: "Projeto"},
            title: "Title",
            tracker: {id: 1, name: "Tracker"},
            user: {id: 2, name: "Fulano"},
            watchers: [{id: 2, name: "Fulano"}, {id: 3, name: "Cicrano"}],
            description: "desc"
        },
        {
            period: "* * * * ",
            project: {id: 0, name: "Projeto"},
            title: "Title",
            tracker: {id: 1, name: "Tracker2"},
            user: {id: 2, name: "Fulano"},
            watchers: [{id: 2, name: "Fulano"}, {id: 3, name: "Cicrano"}],
            description: "desc"
        },
        {
            period: "* * * * ",
            project: {id: 0, name: "Projeto"},
            title: "Title",
            tracker: {id: 1, name: "Tracker3"},
            user: {id: 2, name: "Fulano"},
            watchers: [{id: 2, name: "Fulano"}, {id: 3, name: "Cicrano"}],
            description: "desc"
        }
    ];

    $scope.projectSelected = null;

    $scope.userAssignedSelected = null;

    $scope.watchesSelected = [];

    $scope.trackerSelected = null;

    $scope.projects = [];

    $scope.users = [];

    $scope.trackers = [];

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

});