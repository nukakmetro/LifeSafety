<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./style/styleHeader.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

</head>
<#include "header.ftl">
<body>
<div class="gridColums">
    <div class="leftColumn">
        <div class="card p-3 py-4">
            <div class="text-center">

                <#if postComments>
                    <#if comments?has_content>
                        <#list comments as comment>
                                <div class="card p-3 py-4">

                                <h2>${comment.user_login}</h2>
                                <p>${comment.content}</p>
                                    </div>

                        </#list>
                    <#else>
                        <p>Коментариев нет.</p>
                    </#if>
                    <#if loginIn??>

                        <p>Добавить комментарий</p>
                        <form action="addedcomment" method="post">
                            <input type="hidden" name="postId" value=${postId}>
                            <textarea name="comment" rows="4" cols="50"
                                      placeholder="Введите ваш комментарий"></textarea>
                            <br>
                            <input type="submit" value="Отправить комментарий">
                        </form>
                    <#else>
                        <p>Нужно ввойти для добаления коментария</p>
                    </#if>

                <#else>
                    <p>Комментарии отключены</p>
                </#if>
            </div>
        </div>
    </div>
    <div class="rightColumn">

    </div>
</div>
</body>
</html>