<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Финансовые инструменты</title>
</head>
<body>
    <h1>Финансовые инструменты (FinAsset)</h1>

    <a href="/finasset/create-form">Добавить новый инструмент</a>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Тикер</th>
                <th>Регистрация</th>
                <th>Дата регистрации</th>
                <th>Эмитент</th>
                <th>Форма выпуска</th>
                <th>Номинал</th>
                <th>Количество</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
        <#list finassets as finasset>
            <tr>
                <td>${finasset.id!"—"}</td>
                <td>${finasset.tiker.nameTiker!"—"}</td>
                <td>${finasset.registration!"—"}</td>
                <td>${finasset.dataRegistration!"—"}</td>
                <td>${finasset.emitent!"—"}</td>
                <td>${finasset.formIssue!"—"}</td>
                <td>${finasset.principal!"—"}</td>
                <td>${finasset.amount!"—"}</td>
                <td>
                    <form action="/finasset/delete/${finasset.id}" method="post" style="display:inline;">
                        <button type="submit">Удалить</button>
                    </form>
                    <a href="/finasset/edit-form/${finasset.id}">Изменить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <br>
    <a href="/">На главную</a>
</body>
</html>
