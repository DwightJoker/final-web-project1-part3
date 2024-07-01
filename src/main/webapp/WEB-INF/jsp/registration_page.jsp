<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Регистрация</title>
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
	background-image: url('images/918.jpg');
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

.btn {
	padding: 10px 170px;
	background-color: green;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

.btn:hover {
	background-color: red;
}
</style>
</head>
<body>

	<div class="container">
		<h1>
			Регистрация нового <br />пользователя
		</h1>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="do_registration" />
		
			<div class="form-group">
				<input type="email" class="input-field" placeholder="Введите логин"
					id="login" name="login" required>
			</div>
			<div class="form-group">
				<input type="password" class="input-field" placeholder="Пароль"
					id="loginPassword" name="password" required>
			</div>
			
			<div class="error-message" id="error-message">
				<c:if test="${not (param.regError eq null) }">
					<c:out value="${param.regError}" />
				</c:if>
			</div>
			<button type="submit" class="btn btn-primary">Зарегистрироваться</button>
			<p>
				<a href="Controller?command=go_to_start_page">На главную
					страницу</a>
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