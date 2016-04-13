/**
 * Created by gustavo on 13/04/2016.
 */
var myApp = angular.module("issues", []);

myApp.controller("issueController", function ($scope) {

    $scope.projectSelected = {};

    $scope.userAssignedSelected = {};

    $scope.watchesSelected = {};

    $scope.trackerSelected = {};

    $scope.projects = {};

    $scope.users = {};

    $scope.trackers = {};

    $scope.updateProjects = function () {
        loadAjaxData("/projects/", function (data) {
            $scope.projects = data;
        });
    }

    $scope.updateUsers = function () {
        var id = $scope.projectSelected;
        loadAjaxData('projects/'+id+'/users/', function (data) {
            $scope.users = data;
        });
    }

    $scope.updateTrackers = function () {
        var id = $scope.projectSelected;
        loadAjaxData('projects/'+id+'/trackers/', function (data) {
            $scope.trackers = data;
        });
    }

    $scope.updateUsersAndTrackers = function(){
        $scope.updateUsers();
        $scope.updateTrackers();
    }

    $scope.updateProjects();

});

var loadAjaxData = function (url, sucessCallback) {
    $.ajax({
        type: 'GET',
        url: url,
        dataType: "json",
        error: function () {
        },
        success: sucessCallback
    });
}