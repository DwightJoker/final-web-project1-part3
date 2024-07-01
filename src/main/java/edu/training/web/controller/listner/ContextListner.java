package edu.training.web.controller.listner;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import edu.training.web.dao.connectionpool.ConnectionPool;
import edu.training.web.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListner implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConnectionPool.getInstance();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			System.err.println("Error disposing ConnectionPool: " + e.getMessage());
		}

		AbandonedConnectionCleanupThread.checkedShutdown();
	}
}