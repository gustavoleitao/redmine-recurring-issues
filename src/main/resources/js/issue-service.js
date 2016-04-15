/**
 * Created by Gustavo on 15/04/2016.
 */

myApp.factory("Issue", function () {
    return function (id, period, title, project, tracker, userAssigned, watchers, description) {
        return {
            id: id,
            period: period,
            title: title,
            project: project,
            userAssigned: userAssigned,
            watchers: watchers,
            description: description,
            tracker: tracker
        }
    }
});

myApp.service('issueService', function ($http) {

    this.all = function (sucessCallback) {
        $http.get('/issues/').then(function (data) {
            sucessCallback(data);
        });
    };

    this.remove = function (issue, sucessCallback) {
        $http.delete('issues/' + issue.id).success(function (data) {
            sucessCallback(data);
        });
    };

    this.add = function (issue, sucessCallback) {
        $http.post('issues/', issue).success(function (data) {
            sucessCallback(data);
        });
    };

});
