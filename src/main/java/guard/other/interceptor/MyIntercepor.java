package guard.other.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyIntercepor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(MyIntercepor.class);
	
	/**
	 * 执行时机：视图已经被解析完毕,类似try catch 后的finally
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception e)
			throws Exception {
          logger.info("afterCompletion...");
	}

	/**
	 * 执行时机：controller执行完，视图解析器没有把视图解析成页面,
	 * 对视图做统一的修改，主要体现在Model上
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView mv)
			throws Exception {
         logger.info("postHandler...");
         Map<String, Object> map = mv.getModel();
 		 map.put("test", "append something");
	}

	/**
	 * 执行时机：在执行controller之前来执行
	 * 返回值类型：boolean：true代表放行可以访问controller，false不可以访问controller
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		logger.info("preHander...");
		logger.info(arg2.getClass());
		return true;
	}

}
