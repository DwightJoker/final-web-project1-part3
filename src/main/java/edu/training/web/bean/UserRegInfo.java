package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

import edu.training.web.controller.concrete.implement.UserRole;

public class UserRegInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String email;
	private String password;
	private UserRole role;

	public UserRegInfo() {

	}

	public UserRegInfo(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public UserRegInfo(String login, String email, String password, UserRole role) {
		super();
		this.login = login;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public int hashCode() {
		return Objects.hash(role, email, login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegInfo other = (UserRegInfo) obj;
		return Objects.equals(role, other.role) && Objects.equals(email, other.email)
				&& Objects.equals(login, other.login) && Objects.equals(password, other.password);
	}

}