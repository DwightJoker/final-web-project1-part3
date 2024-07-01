package edu.training.web.controller.concrete.implement;

import java.util.Arrays;
import java.util.List;

public enum UserRole {

	ADMIN("admin"), USER("user"), EDITOR("editor"), GUEST("guest");

	public final String roleName;

	private UserRole(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public static UserRole getUserRole(String value) {

		return getRolesList().stream().filter(x -> x.getRoleName().equals(value)).findFirst().orElse(USER);

	}

	public static List<UserRole> getRolesList() {
		return Arrays.asList(ADMIN, USER, EDITOR, GUEST);
	}

}
