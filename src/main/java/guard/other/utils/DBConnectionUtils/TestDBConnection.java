package guard.other.utils.DBConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 测试数据库连接工具
 * @author guard
 *
 */
public class TestDBConnection {
    
	@Test
	public void TestDBConn(){
		ResultSet rs = DBConnection.getQuery("select * from user where id = ?", new String[]{"1"});
		try {
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.closeAll();
		}
	}
	
	@Test
	public void TestDBConnDataSource(){
		Connection conn = DBConnection_DateSource.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement("select * from user where id = ?");
			psmt.setString(1,"1");
			rs = psmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection_DateSource.closeAll(conn, psmt, rs);
		}
	}
}
