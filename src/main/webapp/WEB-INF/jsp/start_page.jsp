<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ГАВ</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
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
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
	background-image: url('images/cupra.jpg');
	background-size: cover;
	background-position: center;
}

article {
	margin-bottom: 20px;
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

.news-item {
	align-items: center;
	margin-bottom: 15px;
	padding-bottom: 15px;
	border-bottom: 1px solid #eee;
}

.img-fluid {
	width: 200px;
	height: 120px;
	margin-right: 20px;
}

.news-content {
	flex-grow: 1;
}

.news-title {
	color: #007bff;
	font-size: 20px;
}

.news-text {
	font-size: 14px;
	color: #FFFFFF;
}

.news-info {
	font-size: 12px;
	color: #999;
}
</style>
</head>
<body>
	<header>
		<h1>Главный Автомобильный Вестник</h1>
		<h2>Журнал о том, о сём, туда, сюда</h2>
	</header>
	<nav>
		<a href="Controller?command=go_to_log_in_page">Log In</a> | <a
			href="Controller?command=go_to_registration_page">Registration</a>
		<div class="error-message" id="error-message">
			<c:if test="${not (param.logoutError eq null) }">
				<c:out value="${param.logoutError}" />
			</c:if>
		</div>
		<div class="error-message" id="error-message">
			<c:if test="${not (param.deleteMessage eq null) }">
				<c:out value="${param.deleteMessage}" />
			</c:if>
		</div>

	</nav>
	<section>
		<h2>Главные новости недели</h2>
		<c:if test="${(sessionScope.user eq null)}">
			<div class="news-item">
				<c:forEach var="newsList" items="${newsList}">
					<div class="news-container">
						<img src="${newsList.picPath}" alt="Базовый курс Java"
							class="img-fluid">
						<div class="news-content">
							<h3 class="news-title">
								<a href="Controller?command=go_to_log_in_page"
									style="color: #27FF00;">${newsList.title}</a>
							</h3>
							<p class="news-text">${"log in to see full text"}..<a
									href="Controller?command=go_to_log_in_page"
									style="color: #FF0000;">Прочитать новость целиком</a>
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</section>
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