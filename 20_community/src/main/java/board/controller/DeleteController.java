package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class DeleteController {

	private final String command = "delete.bd";
	private final String getPage = "/delete";
	private final String gotoPage = "redirect:/list.bd";
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String delete(@RequestParam("num") String num,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("memberId") String memberId,
							Model model,
							HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		BoardBean board = boardDao.getArticle(num);
		
		if(board.getWriter().equals(memberId) || memberId.equals("admin") ) {
			model.addAttribute("num", num);
			model.addAttribute("pageNumber", pageNumber);
			return getPage;
		}else {
			writer.println("<script>alert('작성자 또는 운영자만 삭제 가능합니다.'); history.go(-1); </script>");
			writer.flush();
			
			return gotoPage;
		}
		
		
	}

	@RequestMapping(value=command, method = RequestMethod.POST)
	public String delete1(@RequestParam("num") String num,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("password") String password ,
							HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		System.out.println("password:"+password);
		System.out.println("num:"+num);
		
		String passwd = boardDao.bringPasswdArticle(num);
		
		if(password == "") {
			writer.println("<script> alert('비밀번호를 제대로 입력해주세요'); history.go(-1); </script>");
			writer.flush();
			return getPage;
		}
		
		if(passwd.equals(password)) {
			//같으면 삭제처리
			BoardBean board = boardDao.getArticle(num);
			
			String deletePath = servletContext.getRealPath("resources");
			File delFile = new File(deletePath+"/"+board.getImage());
			delFile.delete();
					
			boardDao.deleteArticle(num);		
			
			return gotoPage+"?pageNumber="+pageNumber;
		}else {
			//같지않으면 같지않다 경고
			writer.println("<script> alert('비밀번호가 일치하지 않습니다'); history.go(-1); </script>");
			writer.flush();
			return getPage;
		}
		
		

	}
	
}
