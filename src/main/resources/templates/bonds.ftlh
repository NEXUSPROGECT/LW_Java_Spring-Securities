<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Облигации</title>
</head>
<body>
    <h1>Облигации (Bond)</h1>

    <a href="/bond/create-form">Добавить новую облигацию</a>
    <br><br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Финансовый инструмент</th>
                <th>Дата погашения</th>
                <th>Количество купонов</th>
                <th>Размер купона</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
        <#list bonds as bond>
            <tr>
                <td>${bond.id!"—"}</td>
                <td>${bond.finAsset.id!""}</td>
                <td>${bond.dataRepayment!"—"}</td>
                <td>${bond.couponsAmount!"—"}</td>
                <td>${bond.couponsRate!"—"}</td>
                <td>
                    <form action="/bond/delete/${bond.id}" method="post" style="display:inline;">
                        <button type="submit">Удалить</button>
                    </form>
                    <a href="/bond/edit-form/${bond.id}">Изменить</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <br>
    <a href="/">На главную</a>
</body>
</html>
