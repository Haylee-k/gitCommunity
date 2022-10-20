package board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class ReplyController {

	private final String command = "reply.bd";
	private String getPage = "/reply";
	private String gotoPage = "redirect:/list.bd";
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String reply(@RequestParam("num") String num,
						@RequestParam("pageNumber") String pageNumber,
						Model model) {
		
		model.addAttribute("num", num);
		model.addAttribute("pageNumber", pageNumber);
		
		return getPage;	
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String reply1(@ModelAttribute("board") @Valid BoardBean board,
							BindingResult result,
							//@RequestParam("num") String num,
							@RequestParam("pageNumber") String pageNumber,
							Model model,
							HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("reply오류!");

			model.addAttribute("pageNumber", pageNumber);
			
			return getPage;
		}
		
		//1. 원글에서 가져와야 할 정보+ 조회수1
		boardDao.updateReadcount(String.valueOf(board.getNum()));
		BoardBean original = boardDao.getArticle(String.valueOf(board.getNum()));
		
		//2. 달글 삽입
		board.setRef(original.getRef());
		board.setRe_step(original.getRe_step());
		board.setRe_level(original.getRe_level());
		
		System.out.println("original.getRef()"+original.getRef());
		System.out.println("original.getRe_step()"+original.getRe_step());
		System.out.println("original.getRe_level()"+original.getRe_level());
		
		board.setReg_date(new Timestamp(System.currentTimeMillis()));
		board.setIp(request.getRemoteAddr());
		
		MultipartFile multi = board.getUpload();
		
		boardDao.insertReply(board);

		String uploadPath = servletContext.getRealPath("resources");
		System.out.println("uploardPath:"+uploadPath);
		File file = new File(uploadPath+"/"+multi.getOriginalFilename());
		try {
			multi.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("pageNumber", pageNumber);
		return gotoPage;
		
	}
	
	
	
}
