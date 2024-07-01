package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import jakarta.servlet.http.Cookie;

import edu.training.web.bean.AuthInfo;
import edu.training.web.bean.User;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoLogIn implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		System.out.println("Perform user authentication and authorization. Login: " + login);
		try {
			User user = userService.logIn(new AuthInfo(login, password));

			if (user != null) {
				HttpSession session = (HttpSession) request.getSession(true);
				session.setAttribute("user", user);

				String rememberMe = request.getParameter("remember-me");
				if (rememberMe != null) {
					Cookie cookie = new Cookie("remember-me", "token1");
					cookie.setHttpOnly(true);
					cookie.setSecure(true);
					response.addCookie(cookie);
				}

				response.sendRedirect("Controller?command=go_to_main_page");

			} else {

				response.sendRedirect("Controller?command=go_to_log_in_page&authError=Wrong login or password!");

			}

		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect("Controller?command=go_to_log_in_page&authError=Wrong!");
		}
	}

}
