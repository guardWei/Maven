package guard.other.tlbUtils;

/**
 * 自定义标签实现类
 * @author guard
 * @version 2015年12月26日22:59:03
 */
public class WhFunctions {
	/**
	 * 除了性别的方法
	 * @author guard
	 * @param sex
	 * @return
	 */
     public static String showSex(String sex){
    	 String result = "";
    	 if(sex.equals("1")){
    		 result = "男";
    	 }else if(sex.equals("2")){
    		 result = "女";
    	 }else if(sex.equals("3")){
    		 result = "保密";
    	 }
    	 return result;
     }
	/* public static String showSex(int sex){
    	 String result = "";
    	 if(sex == 1){
    		 result = "男";
    	 }else if(sex == 2){
    		 result = "女";
    	 }else if(sex ==3){
    		 result = "保密";
    	 }
    	 return result;
     }*/
}
