package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardDao;
import board.model.CommentBean;

@Controller
public class CommentUpdateController {

	private final String command ="CommentUpdate.bd";
	private final String getPage ="/commentUpdate";
	private final String gotoPage ="redirect:/content.bd";
	
	
	@Autowired
	BoardDao boardDao;

	@RequestMapping(value=command, method=RequestMethod.GET)
	public String cupdate(@RequestParam("bdnum") String bdnum,
							@RequestParam("pageNumber") String pageNumber,
								@RequestParam("renum") String renum,
								@RequestParam("memberId") String memberId,
								Model model,
								HttpServletResponse response  ) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		System.out.println("일단 수정1 renum:"+renum);
		CommentBean comment = boardDao.commentGetOneInfo1(renum);
		
/*		System.out.println("일단 수정2");
		System.out.println(comment.getBdnum());
		System.out.println(comment.getRenum());
		System.out.println(comment.getWriter());
		System.out.println(comment.getContent());
		System.out.println(comment.getReg_date());
*/		
		if(comment.getWriter().equals(memberId)) { //로그인정보가 일치하면
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("comment", comment);
			return getPage;
		}else {  //로그인정보가 일치하지않으면
			writer.println("<script> alert('댓글작성자만 수정가능합니다'); history.go(-1); </script>");
			writer.flush();
		}
		return gotoPage;

	}
	
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String cupdate2(@ModelAttribute("comment") @Valid CommentBean comment,
							@RequestParam("pageNumber") String pageNumber,
								Model model) {
		
		boardDao.commentGetOneInfo1(String.valueOf(comment.getRenum()));
		
		System.out.println("commentUpdate2 :");
/*		System.out.println(comment.getBdnum());
		System.out.println(comment.getRenum());
		System.out.println(comment.getWriter());
		System.out.println(comment.getContent());
		System.out.println(comment.getReg_date());
*/		
		boardDao.commentUpdate(comment);
		
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("num", comment.getBdnum());
		return gotoPage;
	}
}
