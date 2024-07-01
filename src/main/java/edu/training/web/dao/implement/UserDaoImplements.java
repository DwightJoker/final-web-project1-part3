package edu.training.web.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.training.web.bean.AuthInfo;
import edu.training.web.bean.User;
import edu.training.web.bean.UserProfile;
import edu.training.web.bean.UserRegInfo;
import edu.training.web.controller.concrete.implement.UserRole;
import edu.training.web.dao.DaoException;

import edu.training.web.dao.UserDao;
import edu.training.web.dao.connectionpool.ConnectionPool;
import edu.training.web.dao.connectionpool.ConnectionPoolException;

public final class UserDaoImplements implements UserDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	private final String LOG_IN = "SELECT u.login as \"login\", u.password as \"password\", u.idusers as \"idusers\", r.title as \"title\" FROM users u LEFT JOIN users_has_roles uhr on u.idusers = uhr.users_idusers LEFT JOIN roles r on uhr.roles_idroles = r.idroles WHERE u.login = ? AND u.password = ?";

	@Override
	public User logIn(AuthInfo info) throws DaoException {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = connectionPool.takeConnection();
			statement = conn.prepareStatement(LOG_IN);
			statement.setString(1, info.getLogin());
			statement.setString(2, info.getPassword());
			resultSet = statement.executeQuery();
			try {
				if (resultSet.next()) {
					String userLogin = resultSet.getString("login");
					String userPassword = resultSet.getString("password");
					int userId = resultSet.getInt("idusers");
					UserRole userRole =  UserRole.getUserRole(resultSet.getString("title"));
					System.out.println(userLogin + userPassword);
					return new User(userLogin, userPassword, userRole, userId);
				} else {
					throw new DaoException("No such user");
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("BD Error", e);
		} finally {
			connectionPool.closeConnection(resultSet, statement, conn);
		}
	}

	@Override
	public User checkToken(String token) throws DaoException {
		return new User("111@mail.ru", UserRole.ADMIN);
	}

	private final String CHECK_USER_EXIST = "SELECT COUNT(*) FROM users WHERE login = ?";

	@Override
	public boolean userExist(UserRegInfo userRegInfo) throws DaoException {
		Connection connection = null;
		PreparedStatement checkUserStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.takeConnection();
			checkUserStatement = connection.prepareStatement(CHECK_USER_EXIST);
			String login = userRegInfo.getLogin();
			checkUserStatement.setString(1, login);
			resultSet = checkUserStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1) > 0;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			connectionPool.closeConnection(resultSet, checkUserStatement, connection);
		}
		return false;
	}

	private final String REGISTRATION = "INSERT INTO users (login, password) VALUES (?, ?)";

	@Override
	public boolean registration(UserRegInfo userRegInfo) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		try {
			connection = connectionPool.takeConnection();
			String login = userRegInfo.getLogin();
			String password = userRegInfo.getPassword();
			preparedStatement = connection.prepareStatement(REGISTRATION, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();

			generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				int newUserId = generatedKeys.getInt(1);
				setRole(newUserId);
			} else {
				throw new DaoException("Failed to retrieve generated user ID");
			}

			return true;

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Registration failed", e);
		} finally {
			connectionPool.closeConnection(generatedKeys, preparedStatement, connection);
		}
	}

	private static final String GET_ALL_USERS = "SELECT u.login as \"login\", u.password as \"password\", u.idusers as \"idusers\", r.title as \"title\" FROM users u LEFT JOIN users_has_roles uhr on u.idusers = uhr.users_idusers LEFT JOIN roles r on uhr.roles_idroles = r.idroles";

	@Override
	public List<User> getAllUsers() throws DaoException {

		List<User> usersList = new ArrayList<>();

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					int id = resultSet.getInt("idusers");
					String login = resultSet.getString("login");
					String password = resultSet.getString("password");
					UserRole userRole =  UserRole.getUserRole(resultSet.getString("title"));

					User user = new User(login, password, userRole ,id);

					usersList.add(user);
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Cannot take users from the database", e);

		}

		return usersList;
	}

	private static final String SQL_INSERT_ROLE = "INSERT INTO users_has_roles (roles_idroles, users_idusers) VALUES (?, ?)";

	@Override
	public boolean setRole(int userId) throws DaoException {

		Connection con = null;
		PreparedStatement ps = null;
		int roleIdByDefault = 2;
		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SQL_INSERT_ROLE);
			ps.setInt(1, 2);
			ps.setInt(roleIdByDefault, userId);

			return ps.executeUpdate() > 0;
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException("Error to add new role!", e);
		} finally {
			connectionPool.closeConnection(ps, con);
		}
	}

	 private static final String INSERT_PROFILE_INFO = "INSERT INTO usersInfo (FirstName, LastName, Address, PhoneNumber, Sex, users_idusers) VALUES (?, ?, ?, ?, ?, ?)";
	
	@Override
	public boolean changeProfile(UserProfile userProfile, int userId) throws DaoException {
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INSERT_PROFILE_INFO);
            ps.setString(1, userProfile.getFirstName());
            ps.setString(2, userProfile.getLastName());
            ps.setString(3, userProfile.getAddress());
            ps.setString(4, userProfile.getPhone());
            ps.setString(5, userProfile.getSex());
            ps.setInt(6, userId);
            return ps.executeUpdate() > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to add new contacts!", e);
        } finally {
        	connectionPool.closeConnection(ps, con);
        }
    }
	
	private static final String SQL_ROLE_ID = "SELECT idroles FROM roles WHERE title = ?";
	
	 @Override
	    public int getRoleId(String title) throws DaoException {
	        Connection connection = null;
	        ResultSet rs = null;
	        PreparedStatement prst = null;
	        int roleId = 0;
	        try {
	            connection = connectionPool.takeConnection();
	            prst = connection.prepareStatement(SQL_ROLE_ID);
	            prst.setString(1, title);
	            rs = prst.executeQuery();
	            if (rs.next()) {
	                roleId = rs.getInt("idroles");
	            }
	            return roleId;

	        } catch (ConnectionPoolException | SQLException e) {
	            throw new DaoException("Error to get roleId", e);

	        } finally {
	        	connectionPool.closeConnection(rs, prst, connection);
	        }

	    }

	private static final String CHANGE_ROLE = "UPDATE users_has_roles SET roles_idroles = ? WHERE users_idusers = ?";
	
	@Override
    public boolean changeRole(String login,String newRole) throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        int userId = getUserIdByLogin(login);
        int newRoleId = getRoleId(newRole);
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(CHANGE_ROLE);
            ps.setInt(1, newRoleId);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to change role!", e);
        } finally {
        	connectionPool.closeConnection(ps, con);
        }
    }
	
	private static final String SELECT_LOGIN = "SELECT * FROM users WHERE login = ?";

	@Override
    public int getUserIdByLogin(String login) throws DaoException {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement prst = null;
        try {
        	connection = connectionPool.takeConnection();
            prst = connection.prepareStatement(SELECT_LOGIN);
            prst.setString(1, login);

            rs = prst.executeQuery();
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt("idusers");
            }
            return userId;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to return UserId!", e);

        } finally {
        	connectionPool.closeConnection(rs, prst, connection);
        }
    }
	
//private static final String USER_DELETE = "DELETE FROM users u WHERE idusers = ? AND login = ?";
private static final String USER_DELETE = "DELETE u, uhr FROM users u LEFT JOIN users_has_roles uhr on u.idusers = uhr.users_idusers WHERE u.login = ? AND u.idusers = ?";
	
	@Override
    public boolean userDelete(User user) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
			connection = connectionPool.takeConnection();
			String userLogin = user.getLogin();
			int userId = user.getId();
			preparedStatement = connection.prepareStatement(USER_DELETE);
			preparedStatement.setString(1, userLogin);
			preparedStatement.setInt(2, userId);
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
        
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error to delete!", e);
        } finally {
        	connectionPool.closeConnection(preparedStatement, connection);
        }
    }
	
	private static final String USER_DELETE_BY_ID = "DELETE u, uhr FROM users u LEFT JOIN users_has_roles uhr on u.idusers = uhr.users_idusers WHERE u.idusers = ?";
	
	@Override
	public boolean userDeleteById(int userId) throws DaoException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = connectionPool.takeConnection();
	        preparedStatement = connection.prepareStatement(USER_DELETE_BY_ID);
	        preparedStatement.setInt(1, userId);
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (ConnectionPoolException | SQLException e) {
	        throw new DaoException("Error to delete!", e);
	    } finally {
	        connectionPool.closeConnection(preparedStatement, connection);
	    }
	    }

}
