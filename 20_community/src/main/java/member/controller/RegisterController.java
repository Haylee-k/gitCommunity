package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class RegisterController {

	private final String command = "/register.mem";
	private String getPage = "/registerForm"; //회원가입하기 폼
	private final String gotoPage ="redirect:/login.mem";
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String register() {
		
		return getPage;	
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public String register1(@ModelAttribute("member") @Valid MemberBean member,
							BindingResult result) {
		
		if(result.hasErrors()) {
			return getPage; //회원가입하다 에러나면 다시 가입폼으로
		}
		System.out.println("register");
		memberDao.insertMember(member);
		return gotoPage;
	}
	
	
	
			
}
