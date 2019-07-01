package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import corejava.model.Account;


public class ServletMain extends HttpServlet{
	private static final long serialVersionUID = -239435839339073815L;
	
	private Logger log = Logger.getLogger("servlet");
	
	@Override
	@SuppressWarnings("Duplicates")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		if((username == null || "".equals(username)) || (pwd == null || "".equals(pwd))) {
			//throw new IllegalArgumentException();
		}
		log.info(username + ": " + pwd);
		Account a = new Account();
		a.setUsername(username);
		a.setPwd(pwd);
		session.setAttribute("account", a);
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dis.forward(request, response);
	}

	@SuppressWarnings("Duplicates")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		log.info(username + ": " + pwd);
		Account a = new Account();
		a.setUsername(username);
		a.setPwd(pwd);
		session.setAttribute("account", a);
		response.sendRedirect(request.getContextPath()+"/project/show.jsp");
	}
	
}
