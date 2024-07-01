<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Страница находится в разработке</title>
<style>
/* Стили для красивого отображения */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	overflow: hidden; /* Чтобы гиф анимация не прокручивалась */
	background-color: #f0f0f0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

#content {
	text-align: center;
}

.running-text {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	animation: runText 8s linear infinite;
}

@keyframes runText {
            0% { transform: translateX(100%); }
            100% { transform: translateX(-100%); }
        }
</style>
</head>
<body>
	<div id="content">
		<h1>Страница находится в разработке</h1>
		<img src="https://i.gifer.com/5Jy1.gif" alt="loading" width="200">
	</div>
	<div class="running-text">
		<p>Уходи</p>
	</div>
</body>
</html>