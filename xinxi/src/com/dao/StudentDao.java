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
	 * 添加学生信息
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
	 * 查询所有学生信息
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
	 * 删除学生信息
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
	 * 查询学生信息通过sid
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
	 * 更新学生信息
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
	 * 模糊查询
	 * @param db
	 * @return
	 */
	public List<Student> dimQueryStudent(DimBean db,PageTool pt) {
		StringBuilder sql = new StringBuilder("select * from student where  1 = 1");
		//是否传sname
		//sname等于空  不管他
		//select * from student where  1 = 1 and sname like '%张三%'
		if (!("".equals(db.getSname()) || db.getSname() == null)) {
			sql.append(" and sname like '%" + db.getSname() + "%'");
		}
		/**
		 * 1、有起始年龄，有结束年龄 select * from student where  1 = 1 and age >= 10 and age <= 200
		 * 2、没有起始年龄，没有结束年龄   忽略不计
		 * 3、有起始年龄，没有结束年龄select * from student where  1 = 1 and age >= 10
		 * 4、没有开始年龄，有结束年龄select * from student where  1 = 1 and age <= 200
		 */
		
		//是否传startAge
		if (!("".equals(db.getStartAge()) || db.getStartAge() == null)) {
			sql.append("  and age >=" + db.getStartAge());
		}
			
		//是否传endAge
		if (!("".equals(db.getEndAge()) || db.getEndAge() == null)) {
			sql.append(" and age <=" + db.getEndAge());
		}
		
		//拼接分页  limit ？，？
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
	 * 查询学生总条数
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
	 * 分页查询
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
	 * 模糊查询总条数
	 * @param db
	 * @return
	 */
	public int dimQueryCountStudent(DimBean db) {
		StringBuilder sql = new StringBuilder("select count(*) from student where 1 = 1 ");
		
		if (!("".equals(db.getSname()) || db.getSname() == null)) {
			sql.append(" and sname like '%" + db.getSname() + "%'");
		}
		/**
		 * 1、有起始年龄，有结束年龄 select * from student where  1 = 1 and age >= 10 and age <= 200
		 * 2、没有起始年龄，没有结束年龄   忽略不计
		 * 3、有起始年龄，没有结束年龄select * from student where  1 = 1 and age >= 10
		 * 4、没有开始年龄，有结束年龄select * from student where  1 = 1 and age <= 200
		 */
		
		//是否传startAge
		if (!("".equals(db.getStartAge()) || db.getStartAge() == null)) {
			sql.append("  and age >=" + db.getStartAge());
		}
			
		//是否传endAge
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
	 * 批量删除
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
