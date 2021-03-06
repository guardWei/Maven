package guard.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import guard.mybatis.model.User;
import guard.mybatis.service.UserServiceI;

@Controller
@RequestMapping(value = "userController")
public class UserController {
	private UserServiceI userServiceI;

	private static final Logger logger = Logger.getLogger(UserController.class);
	
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
	public String showUser(String id,HttpServletRequest request,Date birthday){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 logger.info("birthday:"+sdf.format(birthday));
		 
    	 User u = userServiceI.getUserById(Long.parseLong(id));
    	 request.setAttribute("user", u);
    	 return "showUser";
     }
	
	/**
	 * 保存用户
	 * @author guard
	 * @date 2015年12月15日18:05:26
	 */
	@RequestMapping("/saveUser")
	public String saveUser(User user){
		userServiceI.saveUser(user);
		logger.info(JSON.toJSONStringWithDateFormat(user,"yyyy:MM:dd"));
		return "showUser";
	}
	
	/**
	 * 根据User的获取User对象
	 * @author guard
	 * @version 2015年12月22日16:53:40
	 * @param uid
	 * @param model
	 */
	@RequestMapping("/getUserById")
	public String getUserByIdWithCourses(String uid,Model model){
		User u = userServiceI.getUserByIdWithCourses(Long.parseLong(uid));
		model.addAttribute("user", u);//相当于把参数值放在request对象里面去
		return "getUserById";
	}
	
	/**
	 * 编辑用户信息
	 * @author guard
	 * @date 2016年3月31日15:18:13
	 */
	@RequestMapping("editUserById")
	public String editUser(String uid,Model model){
		User u = userServiceI.getUserByIdWithCourses(Long.parseLong(uid));
		model.addAttribute("user", u);//相当于把参数值放在request对象里面去
		return "springMvcForm";
	}
	
	/**
	 * xmlHttpRequest获取数据
	 * @author guard
	 * @version 2016年3月7日10:40:13
	 * @param request
	 * @param response
	 */
	@RequestMapping("/xmlHttpRequest")
	public void getData(HttpServletRequest request,HttpServletResponse response){
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("ajaxRequest请求成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注册时间类型的属性编辑器
	 * @author guard
	 * @date 2015年12月15日17:43:45
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
}
