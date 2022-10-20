package admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminMainController {
	
	private final String command = "/main.ad";
	private String getPage = "/adminMain";
	
	
	@RequestMapping(command)
	public String main(Model model) {
		
		return getPage;
	}

}
