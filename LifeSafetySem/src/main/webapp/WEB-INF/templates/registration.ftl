<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./style/styleHeader.css" type="text/css" rel="stylesheet">
    <link href="./style/styleAuth.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

</head>
<#include "header.ftl">
<body>
<main class="form-signin w-100 m-auto">

    <form id="registration" method="post" action="registration">
        <p>Регистрация</p>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="login" name="login" placeholder="name@example.com">
            <label for="floatingInput">Логин</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="password" name="password" placeholder="Пароль">
            <label for="floatingPassword">Пароль</label>
        </div><br>
        <input type="submit" class="btn btn-primary w-100 py-2" value="Зарегистрироваться">
    </form>
</main>
<script
        type="text/javascript" src="./js/scriptRegAuth.js">
</script>
</body>
</html>