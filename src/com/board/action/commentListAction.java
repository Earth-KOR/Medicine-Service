package com.board.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.db.commentDAO;
import com.board.db.commentDTO;

public class commentListAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		
		commentDAO cdao = new commentDAO();
		HttpSession session = request.getSession();
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int count = cdao.commentCount(board_num);
		String id = (String)session.getAttribute("id"); 
		
		List<commentDTO> list = null;
		JSONArray json = new JSONArray();
		
		JSONObject boardInfo = new JSONObject();
		
		json.add(boardInfo);
		
		list = cdao.getAllComment(board_num);
		
		SimpleDateFormat f = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
		
		
		if(list.size() > 0){
	        for(int i=0; i<list.size(); i++){
	        	boardInfo = new JSONObject();
	        
	            String dateString = f.format(list.get(i).getDate());
	            
	            boardInfo.put("id", list.get(i).getId());
	            boardInfo.put("com_con", list.get(i).getCom_con());
	            boardInfo.put("date", dateString);
	            boardInfo.put("board_num", list.get(i).getBoard_num());
	            
	            
	            
	            json.add(boardInfo);
		
	        }
	}
	
	}
}
