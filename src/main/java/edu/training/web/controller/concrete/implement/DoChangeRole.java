package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.bean.User;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoChangeRole implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("Controller?command=go_to_start_page&Info=No rights");
			return;
		}

		User user = (User) session.getAttribute("user");
		UserRole role = user.getRole();
		if (role != UserRole.ADMIN) {
			response.sendRedirect("Controller?command=go_to_start_page&Info=No rights");
			return;
		}

		String login = request.getParameter("login");
		String newRole = request.getParameter("newRole");

		try {
			if (userService.changeRole(login, newRole)) {
				response.sendRedirect("Controller?command=go_to_main_page&Info=Role changed successfully");
			} else {
				response.sendRedirect("Controller?command=go_to_main_page&Info=Change error");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect("Controller?command=go_to_main_page&Info=error");
		}
	}
}
