<!doctype html>
<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="./style/styleHeader.css" type="text/css" rel="stylesheet">
    <link href="./style/styleProfileCard.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">

</head>
<#include "header.ftl">
<body>
<div class="container mt-5">
    <div class="row d-flex justify-content-center">
        <div class="col-md-7">
            <div class="card p-3 py-4">
                <div class="text-center">
                    <#if image??>
                        <img src="${image}" alt="UserImage" width="100" height="100" width="100" class="rounded-circle">
                    <#else>
                        <img src="./images/logo.png" alt="UserImage" width="100" height="100" width="100" class="rounded-circle">
                    </#if>
                </div>
                <div class="text-center mt-3">
                    <span class="bg-secondary p-1 px-4 rounded text-white">New</span>
                    <h5 class="mt-2 mb-0">${user.login}</h5>
                    <span>student</span>

                    <div class="px-4 mt-1">
                        <#if user.description?has_content>
                        <p class="fonts">${user.description} </p>
                            <#else>
                                <p class="fonts">Тут могла бы быть твоя реклама </p>
                        </#if>
                    </div>

                    <div class="buttons">
                        <button class="btn btn-outline-primary px-4">Message</button>
                        <button class="btn btn-primary px-4 ms-3">Contact</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>