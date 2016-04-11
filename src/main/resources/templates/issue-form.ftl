<#import "layout.ftl" as layout />
<@layout.layout>

<form action="http://www.google.com">

    <label for="issue-period" class="small">Periodicidade</label>
    <input id="issue-period" type="text" class="form-control">

    <label for="issue-project" class="small">Projeto</label>
    <select class="form-control" id="issue-project">
        <#list projects as project>
        <option id="${project.id}">${project.name}</option>
        </#list>
    </select>

    <label for="issue-type" class="small">Tipo da tarefa</label>
    <select class="form-control" id="issue-type">
        <option></option>
        <option>Desenvolvimento</option>
        <option>Documentação</option>
        <option>Financeiro</option>
    </select>

    <label for="issue-titulo" class="small">Título da tarefa</label>
    <input id="issue-titulo" type="text" class="form-control">

    <label for="issue-assigned" class="small">Atribuir para</label>
    <select class="form-control" id="issue-assigned" >
        <#list users as user>
            <option id="${user.id}">${user.firstName} ${user.lastName} (${user.login})</option>
        </#list>
    </select>

    <label for="issue-desc" class="small">Descrição</label>
    <textarea class="form-control" rows="3" id="issue-desc"></textarea>

    <label for="issue-watchers" class="small">Observadores</label>
    <select multiple class="form-control" id="issue-watchers">
        <#list users as user>
            <option id="${user.id}">${user.firstName} ${user.lastName} (${user.login})</option>
        </#list>
    </select>

    <div style="float: right;">
        <br/>
        <button type="submit" class="btn btn-primary right">Salvar</button>
        <br/>
    </div>

</form>


</@layout.layout>