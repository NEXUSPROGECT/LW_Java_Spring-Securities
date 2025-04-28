<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Тикеры</title>
</head>
<body>
    <h1>Тикеры ценных бумаг (Tiker)</h1>

    <a href="/tiker/create-form">Добавить новый тикер</a>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Название тикера</th>
                <th>Биржевая площадка</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
        <#list tikers as tiker>
            <tr>
                <td>${tiker.id!"—"}</td>
                <td>${tiker.nameTiker!"—"}</td>
                <td>${tiker.dealPlace!"—"}</td>
                <td>
                    <form action="/tiker/delete/${tiker.id}" method="post" style="display:inline;">
                        <button type="submit">Удалить</button>
                    </form>
                    <a href="/tiker/edit-form/${tiker.id}">Изменить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <br>
    <a href="/">На главную</a>
</body>
</html>