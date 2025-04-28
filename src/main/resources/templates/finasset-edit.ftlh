<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменить Финансовый Инструмент</title>
</head>
<body>
    <h1>Изменить Финансовый Инструмент</h1>

    <form action="/finasset/edit-form/${finasset.id}" method="post">
        <input type="hidden" name="id" value="${finasset.id!"—"}">

        <label for="tiker">Тикер:</label>
        <select name="tiker.id" id="tiker">
          <option value="" disabled selected>— выберите тикер —</option>
          <#list tikers as t>
            <option value="${t.id}"
              <#if finasset.tiker?has_content && finasset.tiker.id == t.id>selected</#if>>
              ${t.nameTiker}
            </option>
          </#list>
        </select>
        <br><br>

        Регистрация: <input type="text" name="registration" value="${finasset.registration!"—"}"><br><br>
        Дата регистрации: <input type="datetime-local" name="dataRegistration" value="${finasset.dataRegistration!"—"}"><br><br>
        Эмитент: <input type="text" name="emitent" value="${finasset.emitent!"—"}"><br><br>
        Форма выпуска: <input type="text" name="formIssue" value="${finasset.formIssue!"—"}"><br><br>
        Номинал: <input type="number" name="principal" value="${finasset.principal!"—"}"><br><br>
        Количество: <input type="number" name="amount" value="${finasset.amount!"—"}"><br><br>

        <button type="submit">Сохранить изменения</button>
    </form>

    <br>
    <a href="/finassets">Назад к списку</a>
</body>
</html>
