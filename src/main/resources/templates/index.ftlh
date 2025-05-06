<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Главная страница</title>
  <!-- Подключение Bootstrap 5 через CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <!-- Навигационная панель -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Главная</a>
    </div>
      <a class="navbar-brand" href="/logout">Выйти из аккаунта</a>
  </nav>

  <!-- Основной контент -->
  <div class="container mt-5">
    <h1 class="mb-4">Выберите таблицу:</h1>
    <ul class="list-group">
      <li class="list-group-item">
        <a href="/finassets" class="text-decoration-none">Финансовые инструменты (FinAsset)</a>
      </li>
      <li class="list-group-item">
        <a href="/tikers" class="text-decoration-none">Тикеры (Tiker)</a>
      </li>
      <li class="list-group-item">
        <a href="/securities" class="text-decoration-none">Акции (Security)</a>
      </li>
      <li class="list-group-item">
        <a href="/bonds" class="text-decoration-none">Облигации (Bond)</a>
      </li>
    </ul>
  </div>

  <!-- Подключение JavaScript Bootstrap -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
