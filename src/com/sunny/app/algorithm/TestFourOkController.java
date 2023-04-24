package com.sunny.app.algorithm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunny.app.Execute;
import com.sunny.app.algorithm.dao.AlgorithmDAO;
import com.sunny.app.algorithm.dto.AlgorithmDTO;
import com.sunny.app.plant.dto.PlantDTO;

public class TestFourOkController implements Execute {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AlgorithmDTO algorithmDTO = new AlgorithmDTO();
		AlgorithmDAO algorithmDAO = new AlgorithmDAO();
		HttpSession session = req.getSession();
		PlantDTO result = algorithmDAO.algorithmResult(algorithmDTO);
		//request.getParameter로 selectTwo의 값 얻어오기
		algorithmDTO.setSelectFour(req.getParameter("selectThree"));
		
		
		System.out.println(req.getParameter("selectThree"));
		
		
		//selectOne String타입으로 저장
		String selectThree = req.getParameter("selectThree");


		// 처리 결과 selectOne 값을 session에 저장합니다.
		session.setAttribute("selectThree", selectThree);

		// testOne.jsp로 selectOne 값을 전달합니다.
		req.getRequestDispatcher("/app/algorithm/testFour.jsp").forward(req, resp);
                                                                                   
	}
}