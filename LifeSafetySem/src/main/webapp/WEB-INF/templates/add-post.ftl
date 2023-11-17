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
    <div class="leftColums">
        <div class="container mt-5">
            <div class="row d-flex justify-content-center">
                <div class="col-md-7">
                    <div class="card p-3 py-4">
                        <div class="text-center">
                            <h1>Создание поста</h1>
                            <div class="card p-3 py-4">
                                <form action="addedpost" method="post">
                                    <div class="postTATitle">
                                        <label for="postTitle">Название поста</label>
                                        <textarea id="postTitle" name="postTitle" placeholder="Название"
                                                  required></textarea>
                                    </div>
                                    <div class="postTAContent">
                                        <label for="postContent">Текст содержимого</label>
                                        <textarea id="postContent" name="postContent" placeholder="Содержимое поста"
                                                  required></textarea>
                                    </div>
                                    <input type="submit" value="Добавить пост">
                                    <div class="checkCom">
                                        <input type="checkbox" id="commentBool" name="commentBool">
                                        <label for="commentBool">Разрешение комментариев</label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="rightColums">

    </div>
</div>
</body>
</html>