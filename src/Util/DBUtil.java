package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * JDBC工具类，简化JDBC编程。
 */
public class DBUtil {
    /**
     * 工具类中的方法都是私有的
     * 工具类的方法都是静态的，不需要new对象，直接采用类名调用
     */
    private static ResourceBundle rb = ResourceBundle.getBundle("jdbc");
    private DBUtil() {
    }

    static {
        try {
            Class.forName(rb.getString("drive"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){

        try {
            Connection conn = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement getStatement(Connection conn){
        try {
            Statement stat = conn.createStatement();
            return stat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
