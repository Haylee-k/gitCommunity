package board.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class ContentControllor {
	
	private final String command="content.bd";
	private final String getPage="/content";
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(command)
	public String content(@RequestParam(value="num",required=true) String num, 
							@RequestParam(value="pageNumber",required=true) String pageNumber,
							Model model) {

		//�۳����� ������ ��ȸ���� 1�� �ö󰡰� 
		//�߰��ߴ� ���� ������ �����;� ��. 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		boardDao.updateReadcount(num);
		BoardBean article = boardDao.getArticle(num);		
		
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("article", article);
		
		return getPage;
		
		
	}
}
