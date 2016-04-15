<#import "layout.ftl" as layout />
<@layout.layout>

<div id="content" ng-app="issues" ng-controller="issueController" ng-init="updateProjects();updateScheduledIssues();">

    <div>
        <table class="table table-striped table-condensed">
            <tr>
                <th>Periodo</th>
                <th>Projeto</th>
                <th>Titulo</th>
                <th>Tipo</th>
                <th>Atribuido para</th>
                <th>Ações</th>
            </tr>
            <tr ng-repeat="scheduledIssue in scheduledIssues">
                <td>{{ scheduledIssue.period }}</td>
                <td>{{ scheduledIssue.project.name }}</td>
                <td>{{ scheduledIssue.title }}</td>
                <td>{{ scheduledIssue.tracker.name }}</td>
                <td>{{ scheduledIssue.userAssigned.fullName }}</td>
                <td><a href="#" ng-click="removeIssue(scheduledIssue)">Remover</a> | <a href="#" ng-click="editIssue(scheduledIssue)">Alterar</a>
                </td>
            </tr>
        </table>
    </div>

    <form>

        <input type="hidden" id="issue-id" ng-model="issue.period.id">

        <label for="issue-period" class="small">Periodicidade</label>
        <input id="issue-period" type="text" class="form-control" ng-model="issue.period">

        <label for="issue-project" class="small">Projeto</label>
        <select class="form-control" id="issue-project" ng-model="issue.project"
                ng-options="project.name for project in projects track by project.id"
                ng-change="updateUsersAndTrackers()">
            <option></option>
        </select>

        <label for="issue-type" class="small">Tipo da tarefa</label>
        <select class="form-control" id="issue-trackers" ng-model="issue.tracker"
                ng-options="tracker.name for tracker in trackers track by tracker.id">
            <option></option>
        </select>

        <label for="issue-titulo" class="small">Título da tarefa</label>
        <input id="issue-titulo" type="text" class="form-control" ng-model="issue.title">

        <label for="issue-assigned" class="small">Atribuir para</label>
        <select class="form-control" id="issue-assigned" ng-model="issue.userAssigned"
                ng-options="userAssigned.fullName for userAssigned in users track by userAssigned.id">
            <opction></opction>
        </select>

        <label for="issue-watchers" class="small">Observadores</label>
        <select multiple class="form-control" id="issue-watchers" ng-model="issue.watchers"
                ng-options="userAssigned.fullName for userAssigned in users track by userAssigned.id">
        </select>

        <label for="issue-desc" class="small">Descrição</label>
        <textarea class="form-control" rows="5" id="issue-desc" ng-model="issue.description"></textarea>

        <div style="float: right;">
            <br/>
            <button type="submit" class="btn btn-primary right" ng-click="clearIssue()">Limpar</button>
            <button type="submit" class="btn btn-primary right" ng-click="addIssue(issue)">Salvar</button>
            <br/>
        </div>

    </form>
</div>

</@layout.layout>