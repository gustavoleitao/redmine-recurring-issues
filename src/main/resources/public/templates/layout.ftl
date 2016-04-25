<#macro layout>
<!DOCTYPE html>
<html lang="pt" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <title>Redmine - Tarefas agendadas</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/jumbotron-narrow.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="js/deps/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/deps/ie-emulation-modes-warning.js"></script>

    <script src="js/deps/angular.js"></script>
    <script src="js/deps/angular-messages.js"></script>
    <script src="js/deps/ngRemoteValidate.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <#--Futura funcionalidade de configurar Redmine e servidor SMTP-->
        <#--<nav>-->
            <#--<ul class="nav nav-pills pull-right">-->
                <#--<li role="presentation"><a href="#">Redmine</a></li>-->
                <#--<li role="presentation"><a href="#">E-mail</a></li>-->
            <#--</ul>-->
        <#--</nav>-->
        <h3 class="text-muted">Redmine - Tarefas Agendadas</h3>
    </div>

    <div class="row">
        <#nested />
    </div>

    <br/>
    <footer class="footer">
        <p>&copy; 2016 Logique Sistemas.</p>
    </footer>

</div>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/deps/ie10-viewport-bug-workaround.js"></script>
<script src="js/deps/jquery-2.2.3.js"></script>
<script src="js/deps/bootstrap.min.js"></script>

<#--Angular Dependencies. Don't change order-->
<script src="js/issue-controller.js"></script>
<script src="js/redmine-service.js"></script>
<script src="js/issue-service.js"></script>

</body>
</html>

</#macro>