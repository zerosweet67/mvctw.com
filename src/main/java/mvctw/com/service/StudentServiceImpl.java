package mvctw.com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvctw.com.bean.Student;
import mvctw.com.dao.StudentDao;
import mvctw.com.dao.StudentDaoImpl;

public class StudentServiceImpl implements StudentService {

	private StudentDaoImpl dao;
	private Connection con;

	public StudentServiceImpl(StudentDaoImpl dao) {
		this.dao = dao;
	}


	@Override
	public List<Student> selectStudent() {
		return dao.findAllStudent();
	}

	@Override
	public Boolean insertStudent(Student student, Integer input) {
	    boolean success = true;
	    try {
	        // 调用 DAO 方法并获取返回值
	        int failedCount = dao.addStudent(student, input);
	        
	        if (failedCount > 0) {
	            success = false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        success = false;
	    }
	    return success;
	}

	public void closeConnection() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
