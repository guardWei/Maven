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
	/*@RequestMapping(value="/{id}/showUser")  那么就可以在访问路径上传参数，同事下面方法的id参数前面要加上@PathVariable
	   @PathVariable和@RequestParam，分别是从路径里面去获取变量，也就是把路径当做变量，后者是从请求里面获取参数。 
	*/
	public String showUser(String id,HttpServletRequest request){
    	 User u = userServiceI.getUserById(Long.parseLong(id));
    	 request.setAttribute("user", u);
    	 return "showUser";
     }
}
