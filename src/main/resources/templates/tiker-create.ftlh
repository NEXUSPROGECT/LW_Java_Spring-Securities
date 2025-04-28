<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить Тикер</title>
</head>
<body>
    <h1>Добавить Тикер</h1>

    <form action="/tiker/create" method="post">
        Название тикера: <input type="text" name="nameTiker"><br><br>
        Торговая площадка: <input type="text" name="dealPlace"><br><br>
        <button type="submit">Сохранить</button>
    </form>

    <br>
    <a href="/tikers">Назад к списку</a>
</body>
</html>