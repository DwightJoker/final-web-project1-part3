package edu.training.web.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.training.web.bean.News;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.NewsDao;
import edu.training.web.dao.connectionpool.ConnectionPool;
import edu.training.web.dao.connectionpool.ConnectionPoolException;

public class NewsDaoImplements implements NewsDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	private final String NEWS_ID = "idnews";
	private final String TITLE = "title";
	private final String TEXT = "text";
	private final String PIC = "pic";

	private final String SQL_NEWS = "SELECT * FROM news ";

	@Override
	public List<News> getNews() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<News> list = new ArrayList<>();
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_NEWS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt(NEWS_ID));
				news.setTitle(resultSet.getString(TITLE));
				news.setText(resultSet.getString(TEXT));
				news.setPicPath(resultSet.getString(PIC));
				list.add(news);
			}
			return list;
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(resultSet, preparedStatement, connection);
		}

	}

	private final String DELETE_NEWS = "DELETE FROM news WHERE idnews = ?";

	@Override
	public void deleteNews(int newsId) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DELETE_NEWS);
			preparedStatement.setInt(1, newsId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(preparedStatement, connection);
		}
	}

	private final String EDIT_NEWS = "UPDATE news SET title = ?, text = ?, pic = ? WHERE idnews = ?";

	@Override
	public void editNews(int newsId, String title, String text, String pic) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(EDIT_NEWS);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, text);
			preparedStatement.setString(3, pic);
			preparedStatement.setInt(4, newsId);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		}
	}

	private final String ADD_NEWS = "INSERT INTO news (title, text, pic) VALUES (?, ?, ?)";

	@Override
	public void addNews(News addNews) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;

		try {
			connection = connectionPool.takeConnection();
			String title = addNews.getTitle();
			String text = addNews.getText();
			String pic = addNews.getPicPath();

			preparedStatement = connection.prepareStatement(ADD_NEWS, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, text);
			preparedStatement.setString(3, pic);

			preparedStatement.executeUpdate();
			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				int newsId = generatedKeys.getInt(1);
				addNews.setId(newsId);
			} else {
				throw new DaoException("Failed to retrieve generated news ID");
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Failed to add news", e);
		} finally {
			connectionPool.closeConnection(generatedKeys, preparedStatement, connection);
		}
	}

}
