<#import "layout.ftl" as layout />
<@layout.layout>

<div id="content" ng-app="issues" ng-controller="issueController">

    <div id="alert-div" class="alert alert-danger alert-dismissible" role="alert" hidden="true">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span>
        </button>
        <span id="alert-text"></span>
    </div>

    <form>

        <label for="issue-period" class="small">Periodicidade</label>
        <input id="issue-period" type="text" class="form-control">

        <label for="issue-project" class="small">Projeto</label>
        <select class="form-control" id="issue-project" ng-model="projectSelected" ng-change="updateUsersAndTrackers()">
            <option ng-repeat="project in projects" value="{{project.id}}">{{project.name}}</option>
            <#--<option value="-1"></option>-->
            <#--<#list projects as project>-->
                <#--<option value="${project.id}">${project.name}</option>-->
            <#--</#list>-->
        </select>

        <label for="issue-type" class="small">Tipo da tarefa</label>
        <select class="form-control" id="issue-trackers" ng-model="trackerSelected">
            <option ng-repeat="tracker in trackers" value="{{tracker.id}}">{{tracker.name}}</option>
        </select>

        <label for="issue-titulo" class="small">Título da tarefa</label>
        <input id="issue-titulo" type="text" class="form-control">

        <label for="issue-assigned" class="small">Atribuir para</label>
        <select class="form-control" id="issue-assigned" ng-model="userAssignedSelected">
            <option ng-repeat="user in users" value="{{user.id}}">{{user.fullName}}</option>
        </select>

        <label for="issue-desc" class="small">Descrição</label>
        <textarea class="form-control" rows="3" id="issue-desc"></textarea>

        <label for="issue-watchers" class="small">Observadores</label>
        <select multiple class="form-control" id="issue-watchers" ng-model="watchesSelected">
            <option ng-repeat="user in users" value="{{user.id}}">{{user.fullName}}</option>
        </select>

        <div style="float: right;">
            <br/>
            <button type="submit" class="btn btn-primary right">Salvar</button>
            <br/>
        </div>

    </form>
</div>



</@layout.layout>