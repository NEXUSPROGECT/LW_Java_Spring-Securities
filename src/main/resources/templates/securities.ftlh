<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Акции</title>
</head>
<body>
    <h1>Акции (Security)</h1>

    <a href="/security/create-form">Добавить новую акцию</a>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Финансовый инструмент</th>
                <th>Дата размещения</th>
                <th>Дата отчета</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
        <#list securities as security>
            <tr>
                <td>${security.id!"-"}</td>
                <td>${security.finAsset.id!"-"}</td>
                <td>${security.dateAccommodation!"-"}</td>
                <td>${security.dateReport!"-"}</td>
                <td>
                    <form action="/security/delete/${security.id}" method="post" style="display:inline;">
                        <button type="submit">Удалить</button>
                    </form>
                    <a href="/security/edit-form/${security.id}">Изменить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <br>
    <a href="/">На главную</a>
</body>
</html>
