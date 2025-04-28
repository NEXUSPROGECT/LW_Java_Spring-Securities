<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Изменить Облигацию</title>
</head>
<body>
    <h1>Изменить Облигацию</h1>

    <form action="/bond/edit-form/${bond.id}" method="post">
        <input type="hidden" name="id" value="${bond.id!"-"}">

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

        Дата погашения: <input type="datetime-local" name="dataRepayment" value="${bond.dataRepayment!"-"}"><br><br>
        Количество купонов: <input type="number" name="couponsAmount" value="${bond.couponsAmount!"-"}"><br><br>
        Размер купона: <input type="number" step="0.01" name="couponsRate" value="${bond.couponsRate!"-"}"><br><br>

        <button type="submit">Сохранить изменения</button>
    </form>

    <br>
    <a href="/bonds">Назад к списку</a>
</body>
</html>
