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
		//����dao��ķ���
		int i = sd.insertStudent(s);
		return i;
	}

	/**
	 * ��ѯ����ѧ����Ϣ
	 * @return
	 */
	public List<Student> queryAllStudent() {
		List<Student> lists = sd.queryAllStudent();
		return lists;
	}

	/**
	 * ɾ��ѧ����Ϣ
	 * @param sid
	 * @return
	 */
	public int deleteStudentBySid(String sid) {
		
		return sd.deleteStudentBySid(sid);
	}

	/**
	 * ͨ��sid��ѯѧ����Ϣ
	 * @param parseInt
	 * @return
	 */
	public Student queryStudentBySid(int sid) {
		return sd.queryStudentBySid(sid);
	}

	/**
	 * ����ѧ����Ϣ
	 * @param s
	 * @return
	 */
	public int updateStudent(Student s) {
		return sd.updateStudent(s);
	}

	/**
	 * ģ����ѯ
	 * @param db
	 * @return
	 */
	public List<Student> dimQueryStudent(DimBean db,PageTool pt) {
		
		return sd.dimQueryStudent(db,pt);
	}

	/**
	 * ��ѯѧ��������
	 * @return
	 */
	public int queryCountStudent() {
		
		return sd.queryCountStudent();
	}
	
	
	/**
	 * ��ҳ��ѯ
	 * @param pt
	 * @return
	 */

	public List<Student> pagingQueryStudent(PageTool pt) {
		return sd.pagingQueryStudent(pt);
	}

	/**
	 * ģ����ѯ��������
	 * @param db
	 * @return
	 */
	public int dimQueryCountStudent(DimBean db) {
		return sd.dimQueryCountStudent(db);
	}

	/**
	 * ����ɾ��
	 * @param sids
	 * @return
	 */
	public int batchDeleteStudent(String sids) {
		// TODO Auto-generated method stub
		return sd.batchDeleteStudent(sids);
	}

}
