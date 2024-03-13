package mvctw.com.demo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import mvctw.com.bean.Student;
import mvctw.com.dao.StudentDaoImpl;
import mvctw.com.dao.StudentDao;
import mvctw.com.service.StudentServiceImpl;
import mvctw.com.service.StudentService;
import mvctw.com.util.IdCardProduce;
import mvctw.com.util.OracleJDBCConnection;
import mvctw.com.util.RandomData;

@WebServlet("/jdbc")
public class JdbcDemo extends HttpServlet {

	private Connection connection;
	private StudentDaoImpl daoImpl;
	private StudentServiceImpl serviceImpl;

	// 首頁
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd1 = req.getRequestDispatcher("/WEB-INF/view/insertStudent.jsp");
		rd1.forward(req, resp);

	}

	/*@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        // 获取请求参数
	        int flag = Integer.parseInt(req.getParameter("flag"));

	        // 创建DAO和Service实例（这里假设使用依赖注入将它们注入Servlet）
	        StudentDaoImpl daoImpl = new StudentDaoImpl();
	        StudentService studentService = new StudentServiceImpl(daoImpl);

	        switch (flag) {
	            case 1:
	                
	                int num = Integer.parseInt(req.getParameter("num"));
	                Student student = new Student();
	                studentService.insertStudent(student, num);
	                req.setAttribute("message", "新增學生資料 : " + student);
	                req.getRequestDispatcher("/WEB-INF/view/insertStudent.jsp").forward(req, resp);
	                break;

	            case 2:
	               
	                List<Student> students = studentService.selectStudent();
	                req.setAttribute("students", students);
	                req.getRequestDispatcher("/WEB-INF/view/showStudent.jsp").forward(req, resp);
	                break;

	            default:
	              
	                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid flag value: " + flag);
	                break;
	        }
	    } catch (NumberFormatException e) {
	       
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid flag value or num value.");
	    } catch (Exception e) {
	        
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
	    }
	}*/
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer flag = Integer.parseInt(req.getParameter("flag"));

		StudentDaoImpl daoImpl = new StudentDaoImpl();
		StudentServiceImpl service = new StudentServiceImpl(daoImpl);
		Student addStudent = new Student();

		if (flag == 1) {

			Integer num = Integer.parseInt(req.getParameter("num"));
			boolean success = service.insertStudent(addStudent, num) ; 
		    if (success) {
		        req.setAttribute("success", true);
		    } else {
		        req.setAttribute("success", false);
		    }
			req.setAttribute("message", num);
			System.out.println("新增學生資料 : " + addStudent);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/insertStudent.jsp");
			rd.forward(req, resp);
		}

		if (flag == 2) {

			List<Student> student = service.selectStudent();
			req.setAttribute("student", student);
			RequestDispatcher rd1 = req.getRequestDispatcher("/WEB-INF/view/showStudent.jsp");
			rd1.forward(req, resp);
		}
	}


}
