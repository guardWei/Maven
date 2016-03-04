package guard.other.utils.DBConnectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * 使用org.apache.tomcat.jdbc.pool.DataSource连接MySQL数据库
 * @author guard
 * @version 2016年3月4日15:32:12
 */
public class DBConnection_DateSource {
	private static DataSource dataSource = new DataSource();

	private static String driverName = "";
	private static String url = "";
	private static String userName = "";
	private static String password = "";

	// 读取属性文件
	static {
		Properties dbProps = new Properties();
		InputStream is = null;
		try {
			is = DBConnection_DateSource.class.getResourceAsStream("/application.properties");
			dbProps.load(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		driverName = dbProps.getProperty("driverClassName");
		url = dbProps.getProperty("jdbc_url");
		userName = dbProps.getProperty("jdbc_username");
		password = dbProps.getProperty("jdbc_password");
	}

	// 配置dataSource
	static {
		PoolProperties p = new PoolProperties();
		p.setDriverClassName(driverName);
		p.setUrl(url);
		p.setUsername(userName);
		p.setPassword(password);

		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(10);
		p.setInitialSize(5);
		p.setMaxWait(10000);
		p.setMaxIdle(10);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		
		dataSource.setPoolProperties(p);
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭所有的 数据对象
	 * @param conn
	 * @param psmt
	 * @param rs
	 */
	public static void closeAll(Connection conn, PreparedStatement psmt, ResultSet rs) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
