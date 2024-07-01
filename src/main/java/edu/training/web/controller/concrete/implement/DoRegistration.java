package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.bean.UserRegInfo;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			try {
				UserRegInfo regInfo = new UserRegInfo(login, password);
				boolean registrationSuccess = userService.registration(regInfo);

				if (registrationSuccess) {
					response.sendRedirect("Controller?command=go_to_start_page&Info=Success registration");
				} else {
					response.sendRedirect("Controller?command=go_to_inprocess_page&Info=Error");
				}

			} catch (ServiceException e) {
				e.printStackTrace();
				response.sendRedirect("Controller?command=go_to_registration_page&Info=Server error");
			}
		} finally {

		}
	}
}
