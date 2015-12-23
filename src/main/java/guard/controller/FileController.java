package guard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/fileController")
public class FileController {
   
	/**
	 * 上传文件(可能遇到的问题http://blog.csdn.net/shenzhen_mydream/article/details/2560112)
	 * @param request
	 * @return
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value="/fileUplode",method=RequestMethod.POST)
	public String fileUplode(HttpServletRequest request) throws FileNotFoundException{
		//第一步转换request
		MultipartHttpServletRequest rm = (MultipartHttpServletRequest) request;
		//获得文件
		CommonsMultipartFile cfile = (CommonsMultipartFile) rm.getFile("ufile");
		
		//获得文件的字节数组
		byte[] byfile = cfile.getBytes(); 
		
		//获得原始文件名
		String originalFileName = cfile.getOriginalFilename();
		//获取文件后缀名（.jpg）
		String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		
		//生成新的文件名
		String fileName = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		fileName = sdf.format(new Date());
		
		//拿到项目的部署路径
		String path = request.getSession().getServletContext().getRealPath("/");
		//定义文件的输出流
		OutputStream out = new FileOutputStream(new File(path+"/uplode/"+fileName+suffix));
		try {
			out.write(byfile);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "fileUplode";
	}
}
