package mvctw.com.dao;

import java.util.List;
import mvctw.com.bean.Student;



public interface StudentDao {
	//新增學生資料
	int addStudent(Student student , Integer input);
	
	//查詢所有學生
	List<Student>findAllStudent();

	

}
