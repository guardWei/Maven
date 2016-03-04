package guard.other.utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC连接MySQL数据库的核心类
 * @author guard
 * @version 2016年3月4日14:16:23
 */
public class DBConnection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";// 驱动信息
	private static final String URL = "jdbc:mysql://localhost:3306/mybatis";// 数据库的链接
	private static final String USERNAME = "root";// 数据库用户名
	private static final String PASSWORD = "123456";// 数据库密码

	private static Connection conn = null; // 数据库连接对象
	private static PreparedStatement psmt = null; // 预执行对象
	private static ResultSet rs = null; // 结果集

	/**
	 * 获取链接
	 */
	public static void getConnection() {
		try {
			Class.forName(DRIVER);// 反射机制获取驱动
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
   
	/**
	 * 底层的查询方法
	 * @param sql
	 * @param arr
	 * @return
	 */
	public static ResultSet getQuery(String sql, String arr[]) {
		getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			if (arr != null && arr.length > 0) {
				for (int i = 0; i < arr.length; i++) {
					psmt.setString(i + 1, arr[i]);
				}
			}
			rs = psmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 数据的变更操作(增，刪，改)
	 * @param sql
	 * @param arr
	 * @return
	 */
	public static int getUpdate(String sql, String arr[]){
		int result = 0;
		getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			if(arr !=null && arr.length>0){
				for(int i = 0;i<arr.length;i++){
					psmt.setString(i+1, arr[i]);
				}
			}
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 关闭所有链接对象
	 */
	public static void closeAll() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
