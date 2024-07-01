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

public class DoDeleteUser implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = (HttpSession) request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("Controller?command=go_to_start_page&authError=Unauthorizedfvsdc");
			return;
		}

		User user = (User) session.getAttribute("user");

		try {
			boolean isDeleted = userService.userDelete(user);

			if (isDeleted) {
				session.invalidate();
				response.sendRedirect("Controller?command=go_to_start_page&authError=Deleted successfully");
			}
			if (UserRole.ADMIN.equals(user.getRole())) {
				response.sendRedirect("Controller?command=go_to_main_page_test&authError=User deleted successfully");

			} else {

				response.sendRedirect("Controller?command=go_to_main_page_test&authError=error");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect("Controller?command=go_to_main_page_test&authError=error");
		}

	}
}
