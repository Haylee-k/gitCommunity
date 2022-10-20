package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class LoginController {
	private final String command ="/login.mem";
	private String getPage = "/loginform";
	private String gotoPage2 = "redirect:/main.ad";
	private String gotoPage = "redirect:/main.mall";
	
	@Autowired
	MemberDao memberdao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String login() {
		return getPage;
	}
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public String login(MemberBean member, 
						HttpSession session,
						HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		MemberBean login = memberdao.getMember(member.getId());
		
		if(login != null) {
			if(member.getPassword().equals(login.getPassword())) {
				//입력한 member.비밀번호가 원래있던정보에서 일치하는 아이디의 login.비밀번호와 같다면 세션에 저장하기
				String destination = (String)session.getAttribute("destination");
				session.setAttribute("loginInfo", login);
				
				if(login.getId().equals("admin")) {
					//불러온 아이디가 admin이면
					return gotoPage2;
				}
				return gotoPage;
			}else {
				//비번이 일치하지않으면
				writer.println("<script>alert('비밀번호가 일치하지 않습니다.');</script>");
				writer.flush();
				return getPage;
			}
		}else {
			//로그인정보가 없으면! null이면
			writer.println("<script>alert('존재하지않는 회원입니다.');</script>");
			writer.flush();
			return getPage;
		}
		
	}
}
