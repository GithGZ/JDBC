import java.sql.*;

/**
 * JDBC完成删除
 */
public class JDBCTest02 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stat = null;
//        * 1、注册驱动
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //        * 2、获取连接
            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,user,password);
//        * 3、获取数据库操作对象
            stat = conn.createStatement();
//        * 4、执行sql
            String sql = "delete from dept where deptno = 40;";
            int n =stat.executeUpdate(sql);
            System.out.println(n==0?"删除失败":"删除成功");
//        * 5、处理查询结果集

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            //        * 6、释放资源
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(stat !=null ) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
