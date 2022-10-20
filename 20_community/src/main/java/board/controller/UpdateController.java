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
		
		//num ,pageNumber �Ѿ��.
		
		//�ۼ����� �������� �ۼ��ڰ� �����ʴٸ� ������ ���������� �ϱ�! 
		
		BoardBean board = boardDao.getArticle(num);

		if(board.getWriter().equals(memberId) || memberId.equals("admin") ) {
			
			model.addAttribute("board", board);
			//model.addAttribute("num", num); �̹� num�� ���� board���������԰� �׷��鼭 ������ num�� ��ܼ� �����ʿ����. 
			model.addAttribute("pageNumber", pageNumber);
			
			return getPage;
		
		}else {
			
			writer.println("<script>alert('�ۼ��ڸ� ���������մϴ�.'); history.go(-1); </script>");
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
			System.out.println("�������� �ȳѾ");
			return getPage;
		}
		
		//*�̹���! �� �Ѿ�Դ���?
		System.out.println("���� �ۼ���: �� �̹��� �����̸�:"+board.getImage());
		System.out.println("���� �ۼ���: ���� �̹��� �����̸�:"+originalImg);
			
		
		//���� ����� �´������� ���ߵ�. 
		//1.�켱 ��ȣ�� �Ѱܼ� ����� ��������!
		String passwd = boardDao.bringPasswdArticle(num);
		
		if(passwd.equals(board.getPasswd())) { // ������ ����� �Է��� board.getPasswd() ����� ���ٸ�? ������Ʈ!
			//�����̹�������
			String originalpath = servletContext.getRealPath("/resources/")+originalImg;
			File file1 = new File(originalpath);
			file1.delete();
			
			//���̹������
			MultipartFile multi = board.getUpload();
			String newPath = servletContext.getRealPath("/resources")+"/"+board.getImage();
			File file2 = new File(newPath);
			try {
				multi.transferTo(file2); //���� ���ε�
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			boardDao.updateArticle(board);
		}else {
			writer.println("<script> alert('��й�ȣ�� ��ġ�����ʽ��ϴ�.'); history.go(-1); </script>");
			writer.flush();
		}
				
		return gotoPage;
		
	}	
	
}
