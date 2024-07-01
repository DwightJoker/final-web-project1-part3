<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
</head>
<body>
    <h2>User Registration</h2>
   <form action="Controller" method="post">
			<input type="hidden" name="command" value="do_registration" />
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <label for="role">Role:</label>
        <select id="role" name="role">
            <option value="ADMIN">Admin</option>
            <option value="USER">User</option>
            <!-- Add more roles as needed -->
        </select><br><br>
        
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
			<p>
				<a href="Controller?command=go_to_start_page">На главную
					страницу</a>
    </form>
</body>
</html>