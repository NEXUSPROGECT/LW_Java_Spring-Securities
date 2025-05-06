<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Вход</title>
  <!-- Подключение Bootstrap 5 через CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <!-- Навигационная панель -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Вход</a>
    </div>
  </nav>

  <!-- Основной контент -->
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <h2 class="mb-4 text-center">Войдите в свой аккаунт</h2>
        <form action="/login" method="post">
          <!-- CSRF токен -->
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

          <div class="mb-3">
            <label for="username" class="form-label">Имя пользователя</label>
            <input type="text" class="form-control" id="username" name="username" required>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="password" name="password" required>
          </div>

          <button type="submit" class="btn btn-primary w-100">Войти</button>
        </form>

        <div class="mt-3 text-center">
          <p>Нет аккаунта? <a href="/register">Зарегистрироваться</a></p>
        </div>
      </div>
    </div>
  </div>

  <!-- Подключение JavaScript Bootstrap -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
