package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.bean.News;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;
import edu.training.web.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddNews implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsTitle = request.getParameter("newsTitle");
		String newsText = request.getParameter("newsText");
		String newsPic = request.getParameter("newsPic");

		News news = new News();
		news.setTitle(newsTitle);
		news.setText(newsText);
		news.setPicPath(newsPic);

		try {
			newsService.addNews(news);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("Controller?command=go_to_main_page");
	}
}