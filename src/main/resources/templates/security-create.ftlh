<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить Акцию</title>
</head>
<body>
    <h1>Добавить Акцию</h1>

    <form action="/security/create" method="post">

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
        Дата размещения: <input type="datetime-local" name="dateAccommodation"><br><br>
        Дата регистрации отчета: <input type="datetime-local" name="dateReport"><br><br>
        <button type="submit">Сохранить</button>
    </form>

    <br>
    <a href="/securities">Назад к списку</a>
</body>
</html>
