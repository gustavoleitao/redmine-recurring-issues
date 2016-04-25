<#import "layout.ftl" as layout />
<@layout.layout>

<div id="content" ng-app="issues" ng-controller="issueController" ng-init="updateProjects();updateScheduledIssues();">

    <div>
        <table class="table table-striped table-condensed">
            <tr>
                <th>Periodicidade</th>
                <th>Projeto</th>
                <th>Título</th>
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
                <td><a href="#" ng-click="removeIssue(scheduledIssue)">Remover</a> | <a href="#" data-toggle="modal"
                                                                                        data-target="#issueModal"
                                                                                        ng-click="editIssue(scheduledIssue)">Alterar</a>
                </td>
            </tr>
        </table>
    </div>

    <div id="issueModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <form name="issueForm">
                <div class="modal-content">
                    <div class="modal-body">
                        <input type="hidden" ng-model="issue.period.id">

                        <label class="small">Título da tarefa</label>
                        <input name="issueTitulo" type="text" class="form-control" ng-model="issue.title" required>
                        <div ng-messages="issueForm.issueTitulo.$error" ng-if="issueForm.issueTitulo.$touched"
                             class="small text-danger">
                            <div ng-message="required">Esse campo é obrigatorio</div>
                        </div>

                        <label class="small">Duração em dias</label>
                        <input type="number" min="0" class="form-control" ng-model="issue.duration" required>

                        <label class="small">Periodicidade (expressão cron)</label>
                        <input type="text" name="issuePeriod" class="form-control" ng-model="issue.period"
                               ng-remote-validate="/issues/cron/validate" required>

                        <div ng-show="issueForm.issuePeriod.$pending" class="small text-primary">Validando...</div>
                        <div ng-show="issueForm.issuePeriod.$error.ngRemoteValidate" class="small text-danger">
                            Expressão cron inválida. Para criar sua expressão <a href="http://www.cronmaker.com/" target="_blank" class="hyperlink">clique aqui</a>
                        </div>

                        <label class="small">Projeto</label>
                        <select class="form-control" ng-model="issue.project"
                                ng-options="project.name for project in projects track by project.id"
                                ng-change="updateUsersAndTrackers()" required>
                            <option></option>
                        </select>

                        <label class="small">Tipo da tarefa</label>
                        <select class="form-control" ng-model="issue.tracker"
                                ng-options="tracker.name for tracker in trackers track by tracker.id" required>
                            <option></option>
                        </select>

                        <label class="small">Atribuir para</label>
                        <select class="form-control" ng-model="issue.userAssigned"
                                ng-options="userAssigned.fullName for userAssigned in users track by userAssigned.id">
                            <opction></opction>
                        </select>

                        <label class="small">Observadores</label>
                        <select multiple class="form-control" ng-model="issue.watchers"
                                ng-options="userAssigned.fullName for userAssigned in users track by userAssigned.id">
                        </select>

                        <label fclass="small">Descrição</label>
                        <textarea class="form-control" rows="5" ng-model="issue.description"></textarea>
                        <br/>
                        <div class="small"><strong>Macros disponíveis:</strong></div>
                        <div class="small">#month - para mês corrente | #day - para o dia corrente | #weekDay - Para o dia da semana | #year - Para o ano corrente </div>

                    </div>
                    <div class="modal-footer">
                        <div style="float: right;">
                            <button type="submit" class="btn btn-primary right" ng-click="clearIssue()">Limpar</button>
                            <button type="submit" class="btn btn-primary right" data-dismiss="modal"
                                    ng-disabled="issueForm.$invalid || issueForm.$pending"
                                    ng-click="addIssue(issue);reset();">
                                Salvar
                            </button>
                            <br/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

<div class="pull-right">
    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#issueModal">Adicionar Tarefa
    </button>
</div>


<!-- Modal -->

</@layout.layout>