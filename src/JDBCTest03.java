import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest03 {
    public static void main(String[] args) throws RuntimeException {
//        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Connection conn = null;
        Statement stat = null;

//        使用资源绑定器绑定属性配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        try {
//            最常用的 通过反射机制
//            1、加载驱动
            Class.forName(rb.getString("driver"));
//            2、获取连接
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
//            3、获取数据库操作对象
            stat = conn.createStatement();
//            4、执行sql
            String sql = "delete from dept where deptno = 40;";
            int n = stat.executeUpdate(sql);
//            5、处理查询结果集

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
//            6、释放资源
        }finally {
            if(stat != null ) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
