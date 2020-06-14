package com.ujiuye.service;

import java.util.List;

import com.ujiuye.bean.DimBean;
import com.ujiuye.bean.PageTool;
import com.ujiuye.bean.Student;
import com.ujiuye.dao.StudentDao;

public class StudentService {
	private StudentDao sd = new StudentDao();

	public int insertStudent(Student s) {
		if (s == null) {
			return 0;
		}
		//调用dao层的方法
		int i = sd.insertStudent(s);
		return i;
	}

	/**
	 * 查询所有学生信息
	 * @return
	 */
	public List<Student> queryAllStudent() {
		List<Student> lists = sd.queryAllStudent();
		return lists;
	}

	/**
	 * 删除学生信息
	 * @param sid
	 * @return
	 */
	public int deleteStudentBySid(String sid) {
		
		return sd.deleteStudentBySid(sid);
	}

	/**
	 * 通过sid查询学生信息
	 * @param parseInt
	 * @return
	 */
	public Student queryStudentBySid(int sid) {
		return sd.queryStudentBySid(sid);
	}

	/**
	 * 更新学生信息
	 * @param s
	 * @return
	 */
	public int updateStudent(Student s) {
		return sd.updateStudent(s);
	}

	/**
	 * 模糊查询
	 * @param db
	 * @return
	 */
	public List<Student> dimQueryStudent(DimBean db,PageTool pt) {
		
		return sd.dimQueryStudent(db,pt);
	}

	/**
	 * 查询学生总条数
	 * @return
	 */
	public int queryCountStudent() {
		
		return sd.queryCountStudent();
	}
	
	
	/**
	 * 分页查询
	 * @param pt
	 * @return
	 */

	public List<Student> pagingQueryStudent(PageTool pt) {
		return sd.pagingQueryStudent(pt);
	}

	/**
	 * 模糊查询的总条数
	 * @param db
	 * @return
	 */
	public int dimQueryCountStudent(DimBean db) {
		return sd.dimQueryCountStudent(db);
	}

	/**
	 * 批量删除
	 * @param sids
	 * @return
	 */
	public int batchDeleteStudent(String sids) {
		// TODO Auto-generated method stub
		return sd.batchDeleteStudent(sids);
	}

}
