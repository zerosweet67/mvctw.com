package mvctw.com.service;

import java.util.List;
import mvctw.com.bean.Student;

public interface StudentService {

	public List<Student> selectStudent(); // 查詢學生資料

	public Boolean insertStudent(Student student,Integer input); // 新增學生資料

}
