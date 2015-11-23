package guard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guard.model.User;
import guard.service.UserServiceI;

@Controller
@RequestMapping(value = "/userController")
public class UserController {
	private UserServiceI userServiceI;

	public UserServiceI getUserServiceI() {
		return userServiceI;
	}

	@Autowired
	public void setUserServiceI(UserServiceI userServiceI) {
		this.userServiceI = userServiceI;
	}
	
	@RequestMapping(value="/showUser")
	public String showUser(String id,HttpServletRequest request){
    	 User u = userServiceI.getUserById(Long.parseLong(id));
    	 request.setAttribute("user", u);
    	 return "showUser";
     }
}
