package com.sunny.app.admin;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunny.app.Execute;
import com.sunny.app.admin.dao.AdminDAO;
import com.sunny.app.admin.dto.AdminDTO;
import com.sunny.app.util.AdminUtils;

public class AdminLoginController implements Execute{
	

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");

		AdminDTO adminDTO = new AdminDTO();
		AdminDAO adminDAO = new AdminDAO();
		String path = "";
		
		
		String adminId = (String)req.getParameter("adminId");
		String adminPW = (String)req.getParameter("adminPassword");
		
		// sha256 비밀번호 암호화 처리 
		adminPW = AdminUtils.pwSha256(adminPW);
		
		// adminDTO 에 입력한 값 담기
		adminDTO.setAdminId(adminId);
		adminDTO.setAdminPassword(adminPW);
        
		//	login 메서드 + 쿼리 실행 후 adminNumber 반환
		Integer adminNumber = adminDAO.login(adminDTO);
		
		//	반환되는 값으로 입력한 정보가 맞는지 확인 후 경로 설정
		if(adminNumber > 0) {
			System.out.println("맞음");
			path = "/admin/select.ad";
			req.getSession().setAttribute("adminNumber", adminNumber);
		}else if(adminNumber == 0) {
			System.out.println("아이디 or 비밀번호 틀림");
			path = "/admin/login.ad?login=fail";
		}
		
		//	경로 확인
		System.out.println("path = " + path);
		//	경로로 페이지 이동
		resp.sendRedirect(path);
	}
}
