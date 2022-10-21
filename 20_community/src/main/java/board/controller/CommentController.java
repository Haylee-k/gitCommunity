package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardDao;
import board.model.CommentBean;

@Controller
public class CommentController {

	private final String command ="comment.bd";
	private final String getPage ="redirect:/content.bd";
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(command)
	public String comment(@ModelAttribute("comment") @Valid CommentBean comment,
							@RequestParam("pageNumber") String pageNumber,
							Model model,
							HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(comment.getWriter()=="") {
			writer.println("<script> alert('로그인 후 작성가능합니다.'); history.go(-1); </script>");
			writer.flush();
		}else {
			
			boardDao.commentInsert(comment);
			
			List<CommentBean> lists = new ArrayList<CommentBean>();
			lists = boardDao.commentList(comment.getBdnum());
			
			System.out.println("test1");
			
			model.addAttribute("num", comment.getBdnum());
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("lists", lists);
			//num=23&pageNumber=1
			return getPage;
		}
		return getPage;

	}
	
	
	
}
