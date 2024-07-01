package edu.training.web.controller.concrete.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.training.web.bean.News;
import edu.training.web.bean.User;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			List<News> newsList;

			User user = (User) session.getAttribute("user");
			if (UserRole.ADMIN.equals(user.getRole())) {
				List<User> usersList;
				try {
					usersList = userService.getAllUsers();
				} catch (ServiceException e) {
					usersList = new ArrayList<>();
					e.printStackTrace();
				}

				request.setAttribute("usersList", usersList);
			}

			try {
				newsList = newsService.getNews();
				if (UserRole.USER.equals(user.getRole()) && newsList.isEmpty()) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/inproсess_page.jsp");
					request.setAttribute("newsList", newsList);
					dispatcher.forward(request, response);

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_page_test.jsp");
					request.setAttribute("newsList", newsList);
					dispatcher.forward(request, response);

				}
			} catch (ServiceException e) {
				e.getMessage();
			}

		} else {
			response.sendRedirect("Controller?command=go_to_start_page&Info=Need to log in");
		}
	}
}
