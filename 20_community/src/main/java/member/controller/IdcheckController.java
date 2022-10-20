package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.model.MemberDao;

@Controller
public class IdcheckController {
	private final String command ="idcheck.mem";
	
	@Autowired
	MemberDao memberdao;
	
	@ResponseBody
	@RequestMapping(command)
	public String idcheck(@RequestParam("inputid") String inputid) {
		
		int cnt=-1;
		cnt = memberdao.idcheck(inputid);
		
		if(cnt==0) {
			return "YES"; //��ġ�ϴ� ���̵� ����
		}
		else {
			return "NO"; //1�̸� ��ġ�ϴ� ���̵��̴�.
		}
		
	}
}
