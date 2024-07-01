package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteNews implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsIdString = request.getParameter("newsId");
		if (newsIdString != null && !newsIdString.isEmpty()) {
			try {
				int newsId = Integer.parseInt(newsIdString);
				newsService.deleteNews(newsId);

				request.setAttribute("deleteMessage", "Новость успешно удалена");
				request.getRequestDispatcher("Controller?command=go_to_main_page").forward(request, response);
			} catch (NumberFormatException e) {

				request.setAttribute("errorMessage", "Неверный формат ID новости");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} catch (ServiceException e) {

				request.setAttribute("errorMessage", "Ошибка при удалении новости: " + e.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} else {

			request.setAttribute("errorMessage", "Не передан ID новости");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}