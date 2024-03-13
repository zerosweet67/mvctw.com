package mvctw.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleJDBCConnection {

	public static void main(String[] args) throws SQLException {

		getConnection();
		closeConnection(getConnection());
	}

	public static Connection getConnection() throws SQLException {
		Connection con = null;
		String dB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "sys as sysdba";
		String pass = "Orcle123";

		try {
			con = DriverManager.getConnection(dB_URL, user, pass);

			if (con != null) {
				System.out.println("資料庫連接成功!");
			} else {
				System.out.println("資料庫連接失敗!");
			}
		} catch (SQLException e) {
			System.err.format("SQL State : %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("關閉成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("關閉失敗");
			}
		}

	}
}
