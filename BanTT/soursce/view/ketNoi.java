package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ketNoi{
	
	public static Connection getConnection () {
		
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String connectionURL="jdbc:sqlserver://LAPTOP-4H6J3REJ:1433;DatabaseName=BanTT;instance=SQLSERVER;encrypt=true;trustServerCertificate=true";
			conn  = DriverManager.getConnection(connectionURL, "sa","123456" );
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ket noi csdl that bai");
			System.err.println(e.getMessage()+"\n"+e.getClass()+"\n"+e.getCause()); 
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) throws SQLException {
        if (conn != null ) {
            conn.close();
        }
    }
}
