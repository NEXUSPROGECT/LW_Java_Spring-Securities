<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменить Акцию</title>
</head>
<body>
    <h1>Изменить Акцию</h1>

    <form action="/security/edit-form/${security.id}" method="post">
        <input type="hidden" name="id" value="${security.id}">
        <label for="finAsset">Финансовый инструмент:</label>
        <select name="finAsset.id" id="finAsset">
          <option value="" disabled selected>— выберите финансовый инструмент —</option>
          <#list finassets as f>
            <option value="${f.id}"
              <#if security.finAsset?has_content && security.finAsset.id == f.id>selected</#if>>
              ${f.id}
            </option>
          </#list>
        </select>
        <br><br>
        Дата размещения: <input type="datetime-local" name="datetime-local" value="${security.dateAccommodation}"><br><br>
        Дата регистрации отчета: <input type="datetime-local" name="datetime-local" value="${security.dateReport}"><br><br>

        <button type="submit">Сохранить изменения</button>
    </form>

    <br>
    <a href="/securities">Назад к списку</a>
</body>
</html>
