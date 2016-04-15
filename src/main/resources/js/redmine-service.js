/**
 * Created by Gustavo on 15/04/2016.
 */

myApp.service('redmineService', function ($http) {

    this.getProjects = function (sucessCallback) {
        $http.get('/projects/').then(function (data) {
            sucessCallback(data);
        });
    };

    this.getUsersByProject = function (id, sucessCallback) {
        $http.get('projects/' + id + '/users/').then(function (data) {
            sucessCallback(data);
        });
    };

    this.getTrackerByProject = function (id, sucessCallback) {
        $http.get('projects/' + id + '/trackers/').then(function (data) {
            sucessCallback(data);
        });
    };

});