package board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;
import board.model.CommentBean;

@Controller
public class ContentControllor {
	
	private final String command="content.bd";
	private final String getPage="/content";
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(command)
	public String content(@RequestParam(value="num",required=true) String num, 
							@RequestParam(value="pageNumber",required=true) String pageNumber,
							//@RequestParam(value="lists",required=false) String lists,
							Model model) {

		//글내용을 누르면 조회수가 1로 올라가고 
		//추가했던 글의 내용을 가져와야 함. 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		boardDao.updateReadcount(num);
		BoardBean article = boardDao.getArticle(num);		
		
		List<CommentBean> lists = new ArrayList<CommentBean>();
		lists = boardDao.commentList(Integer.valueOf(num));
		
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("article", article);
		model.addAttribute("lists", lists);
		
		return getPage;
		
		
	}
}
