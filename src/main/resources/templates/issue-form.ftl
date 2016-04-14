<#import "layout.ftl" as layout />
<@layout.layout>

<div id="content" ng-app="issues" ng-controller="issueController" ng-init="updateProjects()">

    <div>
        <table class="table table-striped table-condensed">
            <tr>
                <th>Periodo</th>
                <th>Projeto</th>
                <th>Titulo</th>
                <th>Tipo</th>
                <th>Atribuido para</th>
            </tr>
            <tr ng-repeat="issue in scheduledIssues">
                <td>{{ issue.period }}</td>
                <td>{{ issue.project.name }}</td>
                <td>{{ issue.title }}</td>
                <td>{{ issue.tracker.name }}</td>
                <td>{{ issue.user.name }}</td>
            </tr>
        </table>
    </div>

    <form>

        <label for="issue-period" class="small">Periodicidade</label>
        <input id="issue-period" type="text" class="form-control">

        <label for="issue-project" class="small">Projeto</label>
        <select class="form-control" id="issue-project" ng-model="projectSelected"
                ng-options="project.name for project in projects"
                ng-change="updateUsersAndTrackers()">
            <option></option>
        </select>

        <label for="issue-type" class="small">Tipo da tarefa</label>
        <select class="form-control" id="issue-trackers" ng-model="trackerSelected"
                ng-options="tracker.name for tracker in trackers">
            <option></option>
        </select>

        <label for="issue-titulo" class="small">Título da tarefa</label>
        <input id="issue-titulo" type="text" class="form-control">

        <label for="issue-assigned" class="small">Atribuir para</label>
        <select class="form-control" id="issue-assigned" ng-model="userAssignedSelected"
                ng-options="user.fullName for user in user">
            <opction></opction>
        </select>

        <label for="issue-watchers" class="small">Observadores</label>
        <select multiple class="form-control" id="issue-watchers" ng-model="watchesSelected"
                ng-options="user.fullName for user in user">
        </select>

        <label for="issue-desc" class="small">Descrição</label>
        <textarea class="form-control" rows="5" id="issue-desc"></textarea>

        <div style="float: right;">
            <br/>
            <button type="submit" class="btn btn-primary right">Salvar</button>
            <br/>
        </div>

    </form>
</div>

</@layout.layout>