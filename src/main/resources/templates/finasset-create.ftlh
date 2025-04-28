<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить Финансовый Инструмент</title>
</head>
<body>
    <h1>Добавить Финансовый Инструмент</h1>

    <form action="/finasset/create" method="post">

        <label for="tiker">Тикер:</label>
        <select name="tiker.id" id="tiker">
          <option value="" disabled selected>— выберите тикер —</option>
          <#list tikers as t>
            <option value="${t.id}"
              <#if finAsset.tiker?has_content && finAsset.tiker.id == t.id>selected</#if>>
              ${t.nameTiker}
            </option>
          </#list>
        </select>
        <br><br>

        Регистрация: <input type="text" name="registration"><br><br>
        Дата регистрации: <input type="datetime-local" name="dataRegistration"><br><br>
        Эмитент: <input type="text" name="emitent"><br><br>
        Форма выпуска: <input type="text" name="formIssue"><br><br>
        Номинал: <input type="number" name="principal"><br><br>
        Количество: <input type="number" name="amount"><br><br>
        <button type="submit">Сохранить</button>
    </form>

    <br>
    <a href="/finassets">Назад к списку</a>
</body>
</html>
