package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoProfileUpdate implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String sex = request.getParameter("sex");

			User user = (User) session.getAttribute("user");
			int userId = user.getId();

			UserProfile userProfile = new UserProfile(firstName, lastName, address, phone, sex);

			try {
				boolean isUpdated = userService.changeProfile(userProfile, userId);
				if (isUpdated) {
					session.setAttribute("updateMessage", "Profile updated successfully!");
				} else {
					session.setAttribute("updateMessage", "Profile update failed!");
				}
			} catch (ServiceException e) {
				throw new ServletException("Error updating profile", e);
			}
		} else {
			session.setAttribute("updateMessage", "User is not logged in!");
		}

		response.sendRedirect("Controller?command=go_to_profile_page");
	}
}