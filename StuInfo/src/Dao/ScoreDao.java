package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 2016/12/19.
 */
public class ScoreDao {
    private String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;
    private ArrayList<HashMap<String, Object>> queryResults = null;

//    public static void main(String[] args) {
//        ScoreDao a = new ScoreDao();
////        System.out.println(a.queryResults.size());
////        for (HashMap<String, Object> t : a.getAllSelectedCor("111111")) {
////            System.out.println(t.toString());
////        }
//        System.out.println(a.getScoreByCName("数据库", "111111").toString());
////
////        System.out.println(a.isExist("C011"));
////        System.out.println(a.isSelect("C011", "111111"));
//
//    }

    public HashMap<String, Object> getScoreByCId(String courseId, String stuId) {
        sql = "select * from score where courseId = ? AND stuId = ?";//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象

        HashMap<String, Object> oneRow = new HashMap<String, Object>();
        try {
            db1.pst.setString(1, courseId);
            db1.pst.setString(2, stuId);
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            ret.next();

            oneRow.put("课程号", ret.getString(3));
            oneRow.put("课程名称", ret.getString(4));
            oneRow.put("学分", ret.getInt(5));
            oneRow.put("平时成绩", ret.getInt(6));
            oneRow.put("期末成绩", ret.getInt(7));
            oneRow.put("综合成绩", ret.getInt(8));

            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            db1.close();//关闭连接
        }
        return oneRow;
    }
    public HashMap<String, Object> getScoreByCName(String courseName, String stuId) {
        sql = "select * from score where courseName = ? AND stuId = ?";//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象

        HashMap<String, Object> oneRow = new HashMap<String, Object>();
        try {
            db1.pst.setString(1, courseName);
            db1.pst.setString(2, stuId);
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            ret.next();

            oneRow.put("课程号", ret.getString(3));
            oneRow.put("课程名称", ret.getString(4));
            oneRow.put("学分", ret.getInt(5));
            oneRow.put("平时成绩", ret.getInt(6));
            oneRow.put("期末成绩", ret.getInt(7));
            oneRow.put("综合成绩", ret.getInt(8));

            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            db1.close();//关闭连接
        }
        return oneRow;
    }
}
