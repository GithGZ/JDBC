import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest04 {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        Connection conn = null;
        Statement stat = null;
//        查询语句专用
        ResultSet rs = null;
        try {
//            1、加载驱动
            Class.forName(rb.getString("driver"));
//            2、获取连接
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
//            3、获取数据库操作对象
            stat = conn.createStatement();
//            4、执行sql
            String sql = "select *from emp";
//          executeUpdate()  insert/update/delete
//            executeQuery() select
            rs = stat.executeQuery(sql);
            while(rs.next()){
                String name =rs.getString("ename");
                System.out.println(name);
            }
//            5、处理查询结果集
//            6、释放资源

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
