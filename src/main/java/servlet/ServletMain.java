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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("dopost ... ...");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		if((username == null || "".equals(username)) 
				|| (pwd == null || "".equals(pwd))) {
			//throw new IllegalArgumentException();
		}
		log.info(username + ": " + pwd);
		Account a = new Account();
		a.setUsername(username);
		a.setPwd(pwd);
		session.setAttribute("account", a);
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dis.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("doget ... ...");
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		log.info(username + ": " + pwd);
		Account a = new Account();
		a.setUsername(username);
		a.setPwd(pwd);
		session.setAttribute("account", a);
		resp.sendRedirect(req.getContextPath()+"/project/show.jsp");
	}
	
}
