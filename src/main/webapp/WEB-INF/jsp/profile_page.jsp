<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Авторизация</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
	background-image: url('images/911.jpg');
	background-size: cover;
	background-position: center;
	overflow-y: auto;
}

header {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
}

nav {
	background-color: #666;
	color: #fff;
	padding: 10px;
	text-align: center;
}

section {
	padding: 20px;
	overflow-y: auto;
}

article {
	margin-bottom: 20px;
}

.container {
	text-align: center;
	max-width: 600px;
	margin: 0 auto;
	background-color: rgba(255, 255, 255, 0.5);
	padding: 20px;
	border-radius: 10px;
}

.input-field {
	width: 100%;
	padding: 15px;
	margin-top: 0px;
	margin-bottom: 0px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
	line-height: 1.5;
}

.input-field::placeholder {
	color: #aaa;
}

.checkbox-label {
	display: flex;
	align-items: center;
	justify-content: flex-start;
	margin-top: 10px;
	text-align: left;
}

.checkbox-input {
	margin-right: 5px;
}

footer {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
	position: fixed;
	bottom: -130px;
	width: 100%;
	transition: bottom 0.3s;
}

footer:hover {
	bottom: 0;
}
</style>
</head>
<body>
	<h1>Профиль пользователя</h1>
	<form action="logout" method="post">
		<input type="submit" value="Выйти из аккаунта">
	</form>
	<div class="container">
		<h1>
			Редактирование профиля <br />пользователя
		</h1>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="do_profile_update" />
			<div class="form-group">
				<input type="text" class="input-field"
					placeholder="Имя пользователя" id="firstName" name="firstName" required>
				<input type="text" class="input-field" 
					placeholder="Фамилия пользователя" id="lastName" name="lastName" required>
				<input type="text" class="input-field" 
					placeholder="Адрес" id="address" name="address" required>
				<input type="text" class="input-field" 
					placeholder="Телефон" id="phone" name="phone" required>
					<input type="text" class="input-field" 
					placeholder="Введите ваш пол(М или Ж)" id="sex" name="sex" required>
   
			</div>
			<button type="submit" class="btn btn-primary">Обновить данные</button>
			<p>
				<a href="Controller?command=go_to_start_page">На главную
					страницу</a>
					<a href="Controller?command=go_to_main_page">Назад</a>
			</p>
		</form>
	</div>
	<footer>
		<nav>
			<a href="Controller?command=go_to_in_process_page">Контакты</a> | <a
				href="Controller?command=go_to_in_process_page">Вакансии</a> | <a
				href="Controller?command=go_to_in_process_page">Обратная связь</a> |
			<a href="Controller?command=go_to_in_process_page">Поддержать</a>
		</nav>
		<p>Рекламное объявление</p>
		<p>ООО Главный Автомобильный Вестник &copy; 2024</p>
	</footer>
</body>
</html>