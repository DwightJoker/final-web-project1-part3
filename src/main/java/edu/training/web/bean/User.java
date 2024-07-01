package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

import edu.training.web.controller.concrete.implement.UserRole;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private UserRole role;
	private int id;

	public User() {
		super();
	}

	public User(UserRole role) {
		super();
		this.role = role;
	}

	public User(String login, String password, int id) {
		super();
		this.login = login;
		this.password = password;
		this.id = id;
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public User(String login, UserRole role) {
		super();
		this.login = login;
		this.role = role;
	}

	public User(String login, String password, UserRole role, int id) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role);
	}

}