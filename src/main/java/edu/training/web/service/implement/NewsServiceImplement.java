package edu.training.web.service.implement;

import java.util.List;

import edu.training.web.bean.News;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.dao.NewsDao;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceException;

public class NewsServiceImplement implements NewsService {

	private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();

	@Override
	public List<News> getNews() throws ServiceException {
		try {
			return newsDao.getNews();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addNews(News addNews) throws ServiceException {
		try {
			newsDao.addNews(addNews);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editNews(int newsId, String title, String text, String pic) throws ServiceException {
		try {
			newsDao.editNews(newsId, title, text, pic);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteNews(int newsId) throws ServiceException {
		try {
			newsDao.deleteNews(newsId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
