package guard.other.utils;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Utils {
	/**
	 * 获取当前年份
	 * @return
	 */
	public static final String GetCurYear(){
		return new java.text.SimpleDateFormat("yyyy").format(
				new Date()).toString();		
	}
	
	/**
	 * 获取当前月份
	 * @return
	 */
	public static final String GetCurMonth(){
		return new java.text.SimpleDateFormat("MM").format(
				new Date()).toString();		
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static final String GetCurDay(){
		return new java.text.SimpleDateFormat("dd").format(
				new Date()).toString();		
	}

	/**
	 * 图片等比缩放工具类
	 * @author zhangww
	 * @version 2015年12月7日15:02:30
	 * @param suffix  文件格式 如：jpg,png
	 * @param sourcePath 源文件
	 * @param targetPath 目标文件
	 * @param ratio 缩放的比例
	 * @throws IOException
	 */
	public static void zoomImage(String suffix,String sourcePath, String targetPath,double ratio) throws IOException{
		
		File srcFile = new File(sourcePath);
		File destFile = new File(targetPath);
		
		BufferedImage bufImg = ImageIO.read(srcFile); 
		Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), bufImg.SCALE_SMOOTH);
		
	    AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null); 
	    Itemp = ato.filter(bufImg, null);
	    ImageIO.write((BufferedImage) Itemp,suffix, destFile);
	    srcFile.delete();//删除缩放前的图片
	}
	/**
	 * 图片裁剪工具类
	 * @author zhangww
	 * @version 2015年12月2日11:18:55
	 * @param suffix 文件格式 如：jpg,png
	 * @param sourcePath 源文件
	 * @param targetPath 目标文件
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @throws IOException
	 */
	 public static void cutImage(String suffix, String sourcePath, String targetPath,  
	            int x1, int y1, int x2, int y2) throws IOException {  
	            File sourceImgFile = new File(sourcePath);  
	            BufferedImage bi = ImageIO.read(sourceImgFile);  
	            int srcWidth = bi.getWidth();  
	            int srcHeight = bi.getHeight(); 
	            
	            int destWidth = x2 - x1;  
	            int destHeight = y2 - y1;  
	            System.out.println("width:"+srcWidth+" height:"+srcHeight+" x1:"+x1+" x2:"+x2+" y1:"+y1+" y2:"+y2);
	            if (srcWidth >= destWidth && srcHeight >= destHeight) {  
	            	 Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);  
	            	 ImageReader reader = (ImageReader)iterator.next();  
	            	 InputStream in=new FileInputStream(sourcePath);  
	            	 ImageInputStream iis = ImageIO.createImageInputStream(in);   
	            	 reader.setInput(iis, true); 
	            	 ImageReadParam param = reader.getDefaultReadParam();   
	            	 Rectangle rect = new Rectangle(x1, y1, x2,y2); 
	            	 param.setSourceRegion(rect); 
	            	 BufferedImage tag = reader.read(0,param);     
	                 ImageIO.write(tag, suffix, new File(targetPath)); 
	                 iis.close();
	                 in.close();
	                 sourceImgFile.delete();  //删除裁剪前的图片
	            }  
	    }
}
