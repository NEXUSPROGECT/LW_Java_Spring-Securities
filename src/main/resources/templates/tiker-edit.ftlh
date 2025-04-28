<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменить Тикер</title>
</head>
<body>
    <h1>Изменить Тикер</h1>

    <form action="/tiker/update" method="post">
        <input type="hidden" name="id" value="${tiker.id!"—"}">
        Название тикера: <input type="text" name="nameTiker" value="${tiker.nameTiker!"—"}"><br><br>
        Торговая площадка: <input type="text" name="dealPlace" value="${tiker.dealPlace!"—"}"><br><br>

        <button type="submit">Сохранить изменения</button>
    </form>

    <br>
    <a href="/tikers">Назад к списку</a>
</body>
</html>
