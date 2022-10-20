package member.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class ListController {
	private final String command = "/list.mem";
	private final String getPage = "/memberList";
	
	@Autowired
	MemberDao memberdao;
	
	@RequestMapping(command)
	public String list(Model model) {
	
		List<MemberBean> list = new ArrayList<MemberBean>();
		list = memberdao.getMemberList();
		
		model.addAttribute("list",list);
		return getPage;		
	}
	
	
	
	
	
	
}
