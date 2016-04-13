
$( "#issue-project" ).change(function() {

    var id = $(this).children(":selected").attr("id")

    loadAjaxData('projects/'+id+'/users/', function(data) {
        setUsers('#issue-assigned', data);
        setUsers('#issue-watchers', data);
    }, "Falha ao carregar usuários do projeto");

    loadAjaxData('projects/'+id+'/trackers/', function (data){
        setTrackers('#issue-type', data);
    }, "Falha ao carregar tipos de tarefas do projeto");

});

var setTrackers = function (idSelect, data) {
    $(idSelect).empty();
    $.each(data, function (i, item) {
        $(idSelect).append($('<option>', {
            value: item.id,
            text : item.name
        }));
    });
};

var setUsers = function(idSelect, data){
    $(idSelect).empty();
    $.each(data, function (i, item) {
        $(idSelect).append($('<option>', {
            value: item.id,
            text : item.firstName + " " + item.lastName
        }));
    });
}

var loadAjaxData = function(url, sucessCallback, errorMsg){
    $.ajax({
        type: 'GET',
        url: url,
        dataType: "json",
        error: function() {
            $('#alert-div').show();
            $('#alert-text').text(errorMsg);
        },
        success: sucessCallback
    });
}

