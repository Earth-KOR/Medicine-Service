package com.serach.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.search.db.serachDAO;

public class Medicine_Info_ListAction {

	
	public void search_medicine_list(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("ListAction : search_list() 실행!");
		
		request.setCharacterEncoding("UTF-8");
		
		serachDAO dao = new serachDAO();
		JSONObject medicine_json = dao.search_medicine_list(request.getParameter("param"));
		
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(medicine_json);
		
		
	}
	
	public void search_effect(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("ListAction : serach_effect() 실행!");
		request.setCharacterEncoding("UTF-8");
		
		serachDAO dao = new serachDAO();
		JSONArray effect_json = dao.search_effect(request.getParameter("param"));
		
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(effect_json);
		
		
		
	}
}
