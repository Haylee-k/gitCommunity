package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UpdateController {

	private final String command ="update.bd";
	private final String getPage ="/update";
	private final String gotoPage ="redirect:/list.bd";
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String update(@RequestParam(value="num", required = true) String num,
							@RequestParam(value="pageNumber", required = true) String pageNumber,
							@RequestParam(value="memberId", required = true) String memberId,
							Model model,
							HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		//num ,pageNumber 넘어옴.
		
		//글수정을 눌렀을때 작성자가 같지않다면 수정에 들어갈수없도록 하기! 
		
		BoardBean board = boardDao.getArticle(num);

		if(board.getWriter().equals(memberId) || memberId.equals("admin") ) {
			
			model.addAttribute("board", board);
			//model.addAttribute("num", num); 이미 num을 통해 board정보가져왔고 그러면서 정보에 num또 담겨서 보낼필요없음. 
			model.addAttribute("pageNumber", pageNumber);
			
			return getPage;
		
		}else {
			
			writer.println("<script>alert('작성자만 수정가능합니다.'); history.go(-1); </script>");
			writer.flush();
			
			return gotoPage;
		}

	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String update1(@ModelAttribute("board") @Valid BoardBean board,
							BindingResult result,
							@RequestParam("num") String num,
							@RequestParam("pageNumber") String pageNumber,
							@RequestParam("originalImg") String originalImg,
							HttpServletResponse response) throws IOException  {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(result.hasErrors()) {
			System.out.println("수정으로 안넘어가");
			return getPage;
		}
		
		//*이미지! 잘 넘어왔는지?
		System.out.println("보드 글수정: 새 이미지 파일이름:"+board.getImage());
		System.out.println("보드 글수정: 원래 이미지 파일이름:"+originalImg);
			
		
		//먼저 비번이 맞는지부터 봐야됨. 
		//1.우선 번호를 넘겨서 비번을 가져오기!
		String passwd = boardDao.bringPasswdArticle(num);
		
		if(passwd.equals(board.getPasswd())) { // 가져온 비번이 입력한 board.getPasswd() 비번과 같다면? 업데이트!
			//원래이미지삭제
			String originalpath = servletContext.getRealPath("/resources/")+originalImg;
			File file1 = new File(originalpath);
			file1.delete();
			
			//새이미지등록
			MultipartFile multi = board.getUpload();
			String newPath = servletContext.getRealPath("/resources")+"/"+board.getImage();
			File file2 = new File(newPath);
			try {
				multi.transferTo(file2); //파일 업로드
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			boardDao.updateArticle(board);
		}else {
			writer.println("<script> alert('비밀번호가 일치하지않습니다.'); history.go(-1); </script>");
			writer.flush();
		}
				
		return gotoPage;
		
	}	
	
}
