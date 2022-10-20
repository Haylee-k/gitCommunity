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
				//�Է��� member.��й�ȣ�� �����ִ��������� ��ġ�ϴ� ���̵��� login.��й�ȣ�� ���ٸ� ���ǿ� �����ϱ�
				String destination = (String)session.getAttribute("destination");
				session.setAttribute("loginInfo", login);
				
				if(login.getId().equals("admin")) {
					//�ҷ��� ���̵� admin�̸�
					return gotoPage2;
				}
				return gotoPage;
			}else {
				//����� ��ġ����������
				writer.println("<script>alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.');</script>");
				writer.flush();
				return getPage;
			}
		}else {
			//�α��������� ������! null�̸�
			writer.println("<script>alert('���������ʴ� ȸ���Դϴ�.');</script>");
			writer.flush();
			return getPage;
		}
		
	}
}
