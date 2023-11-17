<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./style/styleHeader.css" type="text/css" rel="stylesheet">
    <link href="./style/styleProfile.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
</head>
<#include "header.ftl">
<body>
<div class="gridColums">
    <div class="leftColums">
        <#if image??>
            <img src="${image}" alt="UserImage" width="100" height="100">
        <#else>
            <img src="./images/logo.png" alt="UserImage" width="100" height="100">
        </#if>
        <br>
        <h1>Загрузка фотографий</h1>
        <form action="profile" method="post" enctype="multipart/form-data">
            Выберите фотографию: <input type="file" name="photo" id="file"><br>
            <input type="submit" value="Загрузить">
        </form>
    </div>
    <div class="centerColums">
        <form class="profile-group" action="profile" method="post">

            <label>Login</label>
            <input class="input-group" type="text" name="login" value=${user.login} disabled><br>
            <label>Birthday</label>
            <#if user.birthData?has_content>
                <input class="input-group" type="date" name="birthday" value=${user.birthDay}><br>
                <#else>
                    <input class="input-group" type="date" name="birthday" placeholder="Birthday"><br>
            </#if>
            <label>First name</label>
            <#if user.firstname?has_content>
            <input class="input-group" type="text" name="firstName" value=${user.firstName}><br>
            <#else>
                <input class="input-group" type="text" name="firstName" placeholder="First name"><br>
            </#if>
            <label>Second name</label>
            <#if user.secondName?has_content>
            <input class="input-group" type="text" name="secondName" value=${user.secondName}><br>
            <#else>
                <input class="input-group" type="text" name="secondName" placeholder="Second name"><br>
            </#if>
            <input type="submit" name="save" value="Сохранить" ><br>
            <button id="change" value="Изменить"></button>
        </form>
    </div>
    <div class="rightColums">

    </div>
</div>
</body>
</html>