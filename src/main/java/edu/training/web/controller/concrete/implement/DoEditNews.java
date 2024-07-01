package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsIdString = request.getParameter("newsId");
		String title = request.getParameter("newsTitle");
		String text = request.getParameter("newsText");
		String pic = request.getParameter("newsPic");

		if (newsIdString != null && !newsIdString.isEmpty()) {
			try {
				int newsId = Integer.parseInt(newsIdString);
				newsService.editNews(newsId, title, text, pic);

				request.setAttribute("editMessage", "Новость успешно изменена");
				request.getRequestDispatcher("Controller?command=go_to_main_page").forward(request, response);
			} catch (NumberFormatException e) {

				request.setAttribute("errorMessage", "Неверный формат ID новости");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {

			request.setAttribute("errorMessage", "Не передан ID новости");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
