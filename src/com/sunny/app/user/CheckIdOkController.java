package com.sunny.app.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.app.Execute;
import com.sunny.app.user.dao.UserDAO;

public class CheckIdOkController implements Execute {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		
		boolean isTrue = userDAO.checkId(req.getParameter("userId"));
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.print(isTrue ? 0 :1);
		out.close();
		
//		System.out.println(result? "사용 가능한 아이디":"중복된 아이디");
		
	}
}
