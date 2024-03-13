package mvctw.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mvctw.com.bean.Student;
import mvctw.com.util.DatabaseUtilByHikariCP;
import mvctw.com.util.IdCardProduce;
import mvctw.com.util.RandomData;

public class StudentDaoImpl implements StudentDao {
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";

	public StudentDaoImpl() {

	}

	@Override
	public int addStudent(Student student, Integer input) {
		// 開始時間
		Long begin = new Date().getTime();
		int successCount = 0;
		Connection conn = null;
		int failedCount = 0;
		try {
			Class.forName(JDBC_DRIVER);
			Connection connection = DatabaseUtilByHikariCP.getConnection();// 獲取連線
			//connection.close();
			if (connection != null) {
				System.out.println("連線成功");
			} else {
				System.out.println("連線失敗");
			}
			connection.setAutoCommit(false);
			PreparedStatement statement = connection
					.prepareStatement("insert into studentdata(sno,sname,ssex,idcard) values(?,?,?,?)");

			String maxSnoQuery = "SELECT MAX(sno) FROM studentdata";
			Statement maxSnoStatement = connection.createStatement();
			ResultSet maxSnoResultSet = maxSnoStatement.executeQuery(maxSnoQuery);
			String maxSno = null;
			if (maxSnoResultSet.next()) {
				maxSno = maxSnoResultSet.getString(1);
			}

			// 開始進位
			char letter = 'A';
			int number = 1;
			if (maxSno != null) {
				letter = maxSno.charAt(0);
				number = Integer.parseInt(maxSno.substring(1)) + 1;
				if (number > 99) {
					letter++;
					number = 1;
				}
			}

			// 塞資料
			for (int i = 0; i < input; i++) {
				String sno = String.format("%c%02d", letter, number);
				statement.setString(1, sno);
				statement.setString(2, RandomData.FakeName());
				Integer sex = RandomData.FakeSex();
				statement.setInt(3, sex);
				statement.setString(4, IdCardProduce.generateIdCard(sex));

				statement.addBatch();

				number++;
				if (number > 99) {
					letter++;
					number = 1;
				}
			}

			statement.executeBatch();
			connection.commit();

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			failedCount = input;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		Long end = new Date().getTime();

		System.out.println("插入花費時間 : " + (end - begin) / 1000 + " s");
		return failedCount;
	}

	@Override
	public List<Student> findAllStudent() {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT sno, sname, ssex, idcard FROM studentdata ORDER BY sno";
		try {

			Class.forName(JDBC_DRIVER);
			Connection connection = DatabaseUtilByHikariCP.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet resultSet = pst.executeQuery();
			{
				while (resultSet.next()) {
					Student student = new Student();
					student.setSno(resultSet.getString("sno"));
					student.setSname(resultSet.getString("sname"));
					student.setSex(resultSet.getInt("ssex"));
					student.setIdcard(resultSet.getString("idcard"));

					students.add(student);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
}
