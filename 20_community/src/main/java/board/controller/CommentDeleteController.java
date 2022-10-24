package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import board.model.BoardDao;
import board.model.CommentBean;

@Controller
public class CommentDeleteController {

	private final String command ="CommentDelete.bd";
	private final String getPage ="redirect:/content.bd";
	
	@Autowired
	BoardDao boardDao ; 
	
	@RequestMapping(command)
	public String commentDelete(@ModelAttribute("pageNumber") String pageNumber,
								@ModelAttribute("renum") String renum,
								@ModelAttribute("bdnum") String bdnum,
								@ModelAttribute("memberId") String memberId,
								Model model ,
								HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		CommentBean comment = boardDao.commentGetOneInfo1(renum); 
		if(memberId.equals(comment.getWriter())) {
			boardDao.commentDelete(renum);
			//model.addAttribute("pageNumber", pageNumber);
			//model.addAttribute("num", bdnum);
			
			writer.println("<script> alert('댓글이 삭제되었습니다.'); </script>");
			writer.print("<script>location.href = 'content.bd?num="+bdnum+"&pageNumber="+pageNumber+"'; </script>");
			writer.flush();

		}else {
			writer.println("<script> alert('작성자만 댓글삭제 가능합니다.'); history.go(-1); </script>");
			writer.flush();
		}		
		
		return getPage;
	}
	
}
