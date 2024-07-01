package edu.training.web.service;

import java.util.List;

import edu.training.web.bean.News;

public interface NewsService {

	List<News> getNews() throws ServiceException;

	void addNews(News addNews) throws ServiceException;

	void editNews(int newsId, String title, String text, String pic) throws ServiceException;

	void deleteNews(int newsId) throws ServiceException;
}