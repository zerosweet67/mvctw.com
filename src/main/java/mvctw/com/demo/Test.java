package mvctw.com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvctw.com.util.IdCardProduce;
import mvctw.com.util.RandomData;

@WebServlet("/test")
public class Test extends HttpServlet {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	// Database credentials
	static final String USER = "sys as sysdba";
	static final String PASS = "Orcle123";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 開始時間
		Long begin = new Date().getTime();
		Connection connection = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sys as sysdba",
					"Orcle123");// 獲取連線
			if (connection != null) {
				System.out.println("獲取連線成功");
			} else {
				System.out.println("獲取連線失敗");
			}
			// 這裡必須設定為false，我們手動批次提交
			connection.setAutoCommit(false);
			// 這裡需要注意，SQL語句的格式必須是預處理的這種，就是values(?,?,...,?)，否則批次處理不起作用
			PreparedStatement statement = connection
					.prepareStatement("insert into studentdata(sno,sname,ssex,idcard) values(?,?,?,?)");
			// 塞資料
			for (int i = 0; i < 10000; i++) {
				String sno = String.format("%c%02d", 'A', i + 1);
				statement.setString(1, sno);
				statement.setString(2, RandomData.FakeName());
				Integer sex = RandomData.FakeSex();
				statement.setInt(3, sex);
				statement.setString(4, IdCardProduce.generateIdCard(sex));
				// 將要執行的SQL語句先新增進去，不執行
				statement.addBatch();
			}
			// 提交要執行的批次處理，防止 JDBC 執行事務處理
			statement.executeBatch();
			connection.commit();
			// 關閉相關連線
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 結束時間
		Long end = new Date().getTime();
		// 耗時
		System.out.println("10000條資料插入花費時間 : " + (end - begin) / 1000 + " s");
	}

}// end JDBCExample