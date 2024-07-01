package edu.training.web.controller.concrete.implement;

import java.io.IOException;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SetLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String locale = request.getParameter("locale");
		HttpSession session = request.getSession(true);

		session.setAttribute("locale", locale);
		System.out.println("Locale in SetLocale: " + locale);

		String actual = (String) session.getAttribute("actual");
		response.sendRedirect("Controller?command=" + actual);
	}
}