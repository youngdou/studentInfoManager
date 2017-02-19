package Dao;

/**
 * Created by user on 2016/12/19.
 */
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseDao {

    private String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;
    private ArrayList<HashMap<String, Object>> queryResults = null;

//    public static void main(String[] args) {
//        CourseDao a = new CourseDao();
////        System.out.println(a.queryResults.size());
//        for (HashMap<String, Object> t : a.getAllSelectedCor("111111")) {
//            System.out.println(t.toString());
//        }
////
////        System.out.println(a.isExist("C011"));
////        System.out.println(a.isSelect("C011", "111111"));
//
//    }

    public CourseDao() {
        queryResults = new ArrayList<HashMap<String, Object>>();
    }
    public  ArrayList<HashMap<String, Object>> getallcoures() {
//       SELECT * FROM course c INNER JOIN courseSelect cs ON c.courseId = cs.courseId AND cs.stuId = "111111";
        sql = "select * from course";//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象

        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                HashMap<String, Object> oneRow = new HashMap<String, Object>();

                oneRow.put("课程号", ret.getString(1));
                oneRow.put("课程名称", ret.getString(2));
                oneRow.put("学时", ret.getInt(3));
                oneRow.put("学分", ret.getInt(4));
                oneRow.put("老师", ret.getString(5));
                oneRow.put("上课地点", ret.getString(6));
                oneRow.put("上课时间", ret.getString(7));

                queryResults.add(oneRow);
            }//显示数据
            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();//关闭连接
        }
        return queryResults;
    }

    public boolean isExist(String courseId) {
        sql = "select * from course where courseId ='" + courseId+"'";//SQL语句
        System.out.println(sql);
        db1 = new DBHelper(sql);//创建DBHelper对象

        boolean isexist = false;

        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            if (ret.next()) {
                isexist = true;
            } else {
                isexist = false;
            }
            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();//关闭连接
        }
        return isexist;
    }

    public boolean isSelect(String courseId, String stuId) {
        sql = "select * from courseSelect where courseId = ? and stuId = ?";//SQL语句
        System.out.println(sql);
        db1 = new DBHelper(sql);//创建DBHelper对象

        boolean isempty = false;

        try {
            db1.pst.setString(1, courseId);
            db1.pst.setString(2, stuId);

            ret = db1.pst.executeQuery();//执行语句，得到结果集
            if (ret.next()) {
                isempty = true;
            } else {
                isempty = false;
            }
            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();//关闭连接
        }
        return isempty;
    }

    public boolean selectCourse(String courseId, String stuId) {
        //这门课已经被选过了
        if (isSelect(courseId, stuId)) {
            return false;
        }

        //选课
        sql = "INSERT INTO courseSelect VALUES(?, ?)";
        db1 = new DBHelper(sql);//创建DBHelper对象

        Integer result = 0;

        try {
            db1.pst.setString(1, courseId);
            db1.pst.setString(2, stuId);

            result = db1.pst.executeUpdate();//执行语句
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();//关闭连接
        }
        return true;
    }

    public ArrayList<HashMap<String, Object>> getAllSelectedCor(String stuId) {
        sql = "select * from courseSelect where stuId = ?";//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象

        ArrayList<String> CourseIds = new ArrayList<String>();
        ArrayList<HashMap<String, Object>> courses= new ArrayList<HashMap<String, Object>>();

        try {
            //获取所有课程Id
            db1.pst.setString(1, stuId);
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                CourseIds.add(ret.getString(1));
            }//显示数据
            ret.close();

            for (String tid : CourseIds) {
                //根据课程Id获取所有课程信息
                db1.pst =  db1.conn.prepareStatement("SELECT * FROM course WHERE courseId = ?");
                db1.pst.setString(1, tid);
                ret = db1.pst.executeQuery();
                while (ret.next()) {
                    HashMap<String, Object> oneRow = new HashMap<String, Object>();
                    oneRow.put("课程号", ret.getString(1));
                    oneRow.put("课程名称", ret.getString(2));
                    oneRow.put("学时", ret.getInt(3));
                    oneRow.put("学分", ret.getInt(4));
                    oneRow.put("老师", ret.getString(5));
                    oneRow.put("上课地点", ret.getString(6));
                    oneRow.put("上课时间", ret.getString(7));
                    courses.add(oneRow);
                }//显示数据

                ret.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db1.close();//关闭连接
        }
        return courses;
    }
}
