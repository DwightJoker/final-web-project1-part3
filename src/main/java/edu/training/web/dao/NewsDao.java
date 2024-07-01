package edu.training.web.dao;

import java.util.List;

import edu.training.web.bean.News;

public interface NewsDao {

	List<News> getNews() throws DaoException;

	void addNews(News addNews) throws DaoException;

	void editNews(int newsId, String title, String text, String pic) throws DaoException;

	void deleteNews(int newsId) throws DaoException;
}
