<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./style/styleHeader.css" type="text/css" rel="stylesheet">
    <link href="./style/styleMain.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<#include "header.ftl">
<body>
<div class="gridColums">
    <div class="leftColumn">
        <div class="container mt-5">
            <div class="row d-flex justify-content-center">
                <div class="col-md-7">
                    <div class="card p-3 py-4">
                        <div class="text-center">
                            <h1>Посты</h1>

                            <#if posts?has_content>
                                <#list posts as post>
                                    <div class="card p-3 py-4">
                                        <h2>${post.title}</h2>
                                        <p>${post.content}</p>
                                        <p>Автор:</p>
                                        <a href="/LifeSafetySem_war_exploded/profilecard?user_login=${post.user_login}">${post.user_login}</a>
                                        <br>
                                        <#if post.postComments!>
                                            <a href="/LifeSafetySem_war_exploded/addedcomment?postId=${post.postId}">Посмотреть
                                                комментарии</a>
                                        <#else>
                                            <p>Комметарии отключены</p>
                                        </#if>
                                    </div>
                                </#list>

                            </#if>
                        </div>
                    </div>

                </div>

            </div>

        </div>

    </div>


    <div class="rightColumn">
        <li>
            <a href="addedpost">Добавить пост</a>
        </li>
    </div>
</div>
</body>
</html>