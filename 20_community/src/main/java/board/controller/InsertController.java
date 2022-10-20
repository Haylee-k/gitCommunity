package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class InsertController {
	private final String command ="insert.bd";
	private final String getPage ="/insertForm";
	private String gotoPage = "redirect:/list.bd";
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String insert(HttpServletResponse response,
							HttpSession session) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(session.getAttribute("loginInfo")== null ) {
			writer.println("<script>  alert('�α��� �� �̿밡���մϴ�.'); history.go(-1); </script>");
			writer.flush();
		}
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String insert2(@ModelAttribute("board") @Valid BoardBean board, 
							BindingResult result,
							HttpServletRequest request) {
	
		
		if(result.hasErrors()) {
			System.out.println("result.hasErrors ���߰��ϱ� ����");
			return getPage;
		}
		System.out.println(board.getWriter());
		System.out.println(board.getSports());
		System.out.println(board.getSubject());
		System.out.println(board.getContent());
		System.out.println(board.getEmail());
		System.out.println(board.getImage());
		System.out.println(board.getIp()); //�ؿ��� �־��ְ�, ��¥�� �ؿ��� �־��ٰŰ�
		System.out.println(board.getNum()); //mapper���� ���� �ְ�
		System.out.println(board.getPasswd());
		System.out.println(board.getReadcount());
		
		
		MultipartFile multi = board.getUpload();
		System.out.println("multi.getName():"+multi.getName());
		System.out.println("multi.getOriginalFilename()"+multi.getOriginalFilename());
		System.out.println("board.getImage()"+board.getImage());
		
		board.setReg_date(new Timestamp(System.currentTimeMillis()));
		board.setIp(request.getRemoteAddr());
		
		boardDao.insertBoardInfo(board);
		
		String uploadPath = servletContext.getRealPath("resources"); //servlet��ü �����ϱ�!!! 
		System.out.println("uploadPath:"+uploadPath);
		File file = new File(uploadPath+"/"+multi.getOriginalFilename());
		try {
			multi.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return gotoPage;		
	}
	
}
