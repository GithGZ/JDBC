import java.sql.*;

/**
 * JDBC完成update
 */
public class JDBCTest01 {
    public static void main(String[] args) {
        Driver driver = null;
        Connection conn = null;
        Statement st = null;
        try {

//            注册驱动
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

//            获取连接

            String url = "jdbc:mysql://localhost:3306/bjpowernode";  // 统一资源定位符
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,user,password);
//            获取数据库操作对象
            st = conn.createStatement();
//            执行sql
            String sql ="insert into dept(deptno,dname,loc) values(50,'人事部','北京');";
            int n =st.executeUpdate(sql);
            System.out.println(n==0?"保存失败":"保存成功");
//            处理查询结果集
//            释放资源
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(st != null){
                try {
                    st.close();
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