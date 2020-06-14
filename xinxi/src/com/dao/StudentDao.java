package com.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bean.DimBean;
import com.bean.PageTool;
import com.bean.Student;
import com.utils.MyUtils;

public class StudentDao {

	/**
	 * ���ѧ����Ϣ
	 * @param s
	 * @return
	 */
	public int insertStudent(Student s) {
		String sql = "insert into student(sname,age,sex,shobby,sclass,edu) values(?,?,?,?,?,?)";
		Object[] objs = {s.getSname(),s.getAge(),s.getSex(),s.getShobby(),s.getSclass(),s.getEdu()};
		int i = 0;
		try {
			i = MyUtils.qr.update(sql,objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ��ѯ����ѧ����Ϣ
	 * @return
	 */
	public List<Student> queryAllStudent() {
		//sql
		String sql = "select * from student";
		List<Student> lists = null;
		try {
			lists = MyUtils.qr.query(sql, new BeanListHandler<>(Student.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	/**
	 * ɾ��ѧ����Ϣ
	 * @param sid
	 * @return
	 */
	public int deleteStudentBySid(String sid) {
		//sql
		String sql = "delete from student where sid = ?";
		int i = 0;
		try {
			i = MyUtils.qr.update(sql,Integer.parseInt(sid));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ��ѯѧ����Ϣͨ��sid
	 * @param sid
	 * @return
	 */
	public Student queryStudentBySid(int sid) {
		//sql
		String sql = "select * from student where sid = ?";
		Student s = null;
		try {
			s = MyUtils.qr.query(sql, new BeanHandler<>(Student.class),sid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * ����ѧ����Ϣ
	 * @param s
	 * @return
	 */
	
	public int updateStudent(Student s) {
		String sql = "update student set sname = ?,age = ?,sex = ?,sclass = ?,shobby = ?,edu = ? where sid = ?";
		Object[] objs = {s.getSname(),s.getAge(),s.getSex(),s.getSclass(),s.getShobby(),s.getEdu(),s.getSid()};
		int i = 0;
		try {
			i = MyUtils.qr.update(sql,objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * ģ����ѯ
	 * @param db
	 * @return
	 */
	public List<Student> dimQueryStudent(DimBean db,PageTool pt) {
		StringBuilder sql = new StringBuilder("select * from student where  1 = 1");
		//�Ƿ�sname
		//sname���ڿ�  ������
		//select * from student where  1 = 1 and sname like '%����%'
		if (!("".equals(db.getSname()) || db.getSname() == null)) {
			sql.append(" and sname like '%" + db.getSname() + "%'");
		}
		/**
		 * 1������ʼ���䣬�н������� select * from student where  1 = 1 and age >= 10 and age <= 200
		 * 2��û����ʼ���䣬û�н�������   ���Բ���
		 * 3������ʼ���䣬û�н�������select * from student where  1 = 1 and age >= 10
		 * 4��û�п�ʼ���䣬�н�������select * from student where  1 = 1 and age <= 200
		 */
		
		//�Ƿ�startAge
		if (!("".equals(db.getStartAge()) || db.getStartAge() == null)) {
			sql.append("  and age >=" + db.getStartAge());
		}
			
		//�Ƿ�endAge
		if (!("".equals(db.getEndAge()) || db.getEndAge() == null)) {
			sql.append(" and age <=" + db.getEndAge());
		}
		
		//ƴ�ӷ�ҳ  limit ������
		sql.append("  limit " + pt.getStartIndex() + "," + pt.getPageSize());
		System.out.println(sql.toString());
		List<Student> lists = null;
		try {
			lists = MyUtils.qr.query(sql.toString(),new BeanListHandler<>(Student.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	/**
	 * ��ѯѧ��������
	 * @return
	 */
	public int queryCountStudent() {
		String sql = "select count(*) from student";
		long l = 0;
		try {
			l = (long)MyUtils.qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (int)l;
	}

	/**
	 * ��ҳ��ѯ
	 * @param pt
	 * @return
	 */
	public List<Student> pagingQueryStudent(PageTool pt) {
		String sql = "select * from student limit ?,?";
		List<Student> lists = null;
		try {
			lists = MyUtils.qr.query(sql, new BeanListHandler<>(Student.class),
					pt.getStartIndex(),pt.getPageSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	/**
	 * ģ����ѯ������
	 * @param db
	 * @return
	 */
	public int dimQueryCountStudent(DimBean db) {
		StringBuilder sql = new StringBuilder("select count(*) from student where 1 = 1 ");
		
		if (!("".equals(db.getSname()) || db.getSname() == null)) {
			sql.append(" and sname like '%" + db.getSname() + "%'");
		}
		/**
		 * 1������ʼ���䣬�н������� select * from student where  1 = 1 and age >= 10 and age <= 200
		 * 2��û����ʼ���䣬û�н�������   ���Բ���
		 * 3������ʼ���䣬û�н�������select * from student where  1 = 1 and age >= 10
		 * 4��û�п�ʼ���䣬�н�������select * from student where  1 = 1 and age <= 200
		 */
		
		//�Ƿ�startAge
		if (!("".equals(db.getStartAge()) || db.getStartAge() == null)) {
			sql.append("  and age >=" + db.getStartAge());
		}
			
		//�Ƿ�endAge
		if (!("".equals(db.getEndAge()) || db.getEndAge() == null)) {
			sql.append(" and age <=" + db.getEndAge());
		}
		long l = 0;
		try {
			l = (long)MyUtils.qr.query(sql.toString(), new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int)l;
	}

	/**
	 * ����ɾ��
	 * @param sids
	 * @return
	 */
	public int batchDeleteStudent(String sids) {
		//sql
		// 1,2,3      in('1,2,3')
		String sql = "delete from student where sid in(" + sids + ")";
		int i = 0;
		try {
			i = MyUtils.qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
