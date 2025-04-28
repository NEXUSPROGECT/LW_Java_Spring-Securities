<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить Облигацию</title>
</head>
<body>
    <h1>Добавить Облигацию</h1>

    <form action="/bond/create" method="post">

        <label for="finAsset">Финансовый инструмент:</label>
        <select name="finAsset.id" id="finAsset">
          <option value="" disabled selected>— выберите финансовый инструмент —</option>
          <#list finassets as f>
            <option value="${f.id}"
              <#if bond.finAsset?has_content && bond.finAsset.id == f.id>selected</#if>>
              ${f.id}
            </option>
          </#list>
        </select>
        <br><br>

        Дата погашения: <input type="datetime-local" name="dataRepayment"><br><br>
        Количество купонов: <input type="number" name="couponsAmount"><br><br>
        Размер купона: <input type="number" step="0.01" name="couponsRate"><br><br>
        <button type="submit">Сохранить</button>
    </form>

    <br>
    <a href="/bonds">Назад к списку</a>
</body>
</html>
