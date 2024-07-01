<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ГАВ</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    display: flex;
    justify-content: space-between;
}

.profile-button {
    order: -1;
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
    background-image: url('images/718.jpg');
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
    display: flex;
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

.hidden {
    display: none;
}

.edit-fields {
    display: none;
}

.edit-toggle:checked + label + .edit-form .edit-fields {
    display: block;
}
.add-fields {
    display: none;
}

.add-toggle:checked + label + .add-form .add-fields {
    display: block;
}
</style>
</head>
<body>
    <header>
        <h1>Главный Автомобильный Вестник</h1>
        <h2>Новости</h2>
    </header>

    <nav>
        <a href="Controller?command=go_to_profile_page">Редактировать данные аккаунта</a> 
        <a href="Controller?command=do_log_out">Выйти из аккаунта</a> 
        <a href="Controller?command=do_delete_user">Удалить аккаунт</a>
    </nav>

    <div class="admin-message" id="admin-message">
            <c:choose>
        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
            <div class="users-list">
                <h2>Список Пользователей</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Login</th>
                            <th>Role</th>
                            <th>Действия</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${usersList}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                                <td>
                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="do_change_role">
                                        <input type="hidden" name="login" value="${user.login}">
                                        <select name="newRole">
                                            <option value="USER" <c:if test="${user.role eq 'USER'}">selected</c:if>>Пользователь</option>
											<option value="EDITOR" <c:if test="${user.role eq 'EDITOR'}">selected</c:if>>Редактор</option>
											<option value="ADMIN" <c:if test="${user.role eq 'ADMIN'}">selected</c:if>>Администратор</option>
                                        </select>
                                        <button type="submit">Изменить роль</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="Controller" method="get">
                                        <input type="hidden" name="command" value="do_delete_user_by_id">
                                        <input type="hidden" name="userId" value="${user.id}">
                                        <button type="submit">Удалить пользователя</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
</div>
                <div class="news-list">
                    <h2>Список статей</h2>
                    
                    <div class="news-add">
                    <input type="checkbox" id="addToggle" class="add-toggle hidden">
					<label for="addToggle" class="add-label btn btn-primary">Добавить новость</label>
                    
                    <form action="Controller" method="post" class="add-form">
					    <input type="hidden" name="command" value="do_add_news">
					    
					    <div class="add-fields">
				        <input type="text" name="newsTitle" placeholder="Заголовок новости">
				        <input type="text" name="newsText" placeholder="Текст новости"></input>
				        <input type="text" name="newsPic" placeholder="Путь к изображению">
				        <button type="submit">Сохранить</button>
					    </div>
					</form>
                    </div>
                    <c:forEach var="news" items="${newsList}">
                        <div class="news-item">
                            <img src="${news.picPath}" alt="${news.title}" class="img-fluid">
                            <div class="news-content">
                                <h3 class="news-title">${news.title}</h3>
                                <p class="news-text">${news.text}</p>

                                <input type="checkbox" id="editToggle-${news.id}" class="edit-toggle hidden">
                                <label for="editToggle-${news.id}" class="edit-label btn btn-primary">Редактировать новость</label>

                                <form action="Controller" method="get" class="edit-form">
                                    <input type="hidden" name="command" value="do_edit_news">
                                    <input type="hidden" name="newsId" value="${news.id}">

                                    <div class="edit-fields">
                                        <input type="text" name="newsTitle" value="${news.title}">
                                        <input type="text" name="newsText" value="${news.text}">
                                        <input type="text" name="newsPic" value="${news.picPath}">
                                        <button type="submit">Сохранить</button>
                                    </div>
                                </form>

                                <form action="Controller" method="get" style="display:inline;">
                                    <input type="hidden" name="command" value="do_delete_news">
                                    <input type="hidden" name="newsId" value="${news.id}">
                                    <button type="submit">Удалить новость</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:when test="${sessionScope.user.role eq 'EDITOR'}">
                <div class="news-list">
                    <h2>Список статей</h2>
                    <c:forEach var="news" items="${newsList}">
                        <div class="news-item">
                            <img src="${news.picPath}" alt="${news.title}" class="img-fluid">
                            <div class="news-content">
                                <h3 class="news-title">${news.title}</h3>
                                <p class="news-text">${news.text}</p>

                                <input type="checkbox" id="editToggle-${news.id}" class="edit-toggle hidden">
                                <label for="editToggle-${news.id}" class="edit-label btn btn-primary">Редактировать новость</label>

                                <form action="Controller" method="get" class="edit-form">
                                    <input type="hidden" name="command" value="do_edit_news">
                                    <input type="hidden" name="newsId" value="${news.id}">

                                    <div class="edit-fields">
                                        <input type="text" name="newsTitle" value="${news.title}">
                                        <input type="text" name="newsText" value="${news.text}">
                                        <input type="text" name="newsPicPath" value="${news.picPath}">
                                        <button type="submit">Сохранить</button>
                                    </div>
                                </form>

                                <form action="Controller" method="get" style="display:inline;">
                                    <input type="hidden" name="command" value="do_delete_news">
                                    <input type="hidden" name="newsId" value="${news.id}">
                                    <button type="submit">Удалить новость</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:when test="${sessionScope.user.role eq 'USER'}">
                <h1>Последние Новости</h1>
                <c:forEach var="newsList" items="${newsList}">
                    <div>
                        <h2>${newsList.title}</h2>
                        <p>${newsList.text}</p>
                    </div>
                    <hr>
                </c:forEach>
            </c:when>
        </c:choose>
    </div>

    <div class="error-message" id="error-message">
        <c:if test="${not empty param.authError}">
            <c:out value="${param.authError}" />
        </c:if>
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