package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoDeleteUserById implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("Controller?command=go_to_start_page&authError=Unauthorized");
			return;
		}

		String userIdStr = request.getParameter("userId");
		if (userIdStr == null || userIdStr.isEmpty()) {
			response.sendRedirect("Controller?command=go_to_main_page&authError=Invalid user ID");
			return;
		}

		try {
			int userId = Integer.parseInt(userIdStr);
			boolean isDeleted = userService.userDeleteById(userId);

			if (isDeleted) {
				response.sendRedirect("Controller?command=go_to_main_page&authError=User deleted successfully");
			} else {
				response.sendRedirect("Controller?command=go_to_main_page&authError=Failed to delete user");
			}
		} catch (ServiceException | NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("Controller?command=go_to_main_page&authError=Error deleting user");
		}
	}

}
