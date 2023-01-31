import java.sql.*;
import java.util.*;

/**
 * 模拟用户登陆功能实现
 * 描述：程序运行提供输入窗口，输入用户名和密码
 * 提交输入信息，Java程序收集信息
 * 连接数据库判断
 *
 * 黑客sql注入  直接登录
 * 用户名：
 * 1
 * 密码：
 * a'or'1'='1
 * 登陆成功
 * 解决方法
 * PrepareStatement 解决
 *
 * 通过。setString传值
 * setString第一个值为问号的位置第一个为1，第二个值为参数
 *
 * 区别
 * Statement 编译一次执行一次
 * PrepareStatement 编译一次执行n次，效率高，更安全有安全检查
 * PrepareStatement 一般使用
 * satement 支持sql注入 需要sql语句拼接的时候只能用statement
 */
public class JDBCTest05 {
    public static void main(String[] args) {
//        初始化界面
        Map map = initUI();
//        验证账号密码
        boolean res = login(map);
        System.out.println(res?"登陆成功":"登陆失败");
    }

    public static boolean login(Map<String,String> map) {
        Connection conn = null;
        PreparedStatement pst = null;
//        Statement stat = null; 不安全
        ResultSet rs = null;
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        try {
            Class.forName(rb.getString("driver"));
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("password"));
//            stat = conn.createStatement(); 不安全
//            String sql = "select * from user_login where loginName='"+map.get("user")+"' and loginPwd='"+map.get("pwd")+"';";
//            一个问号表示一个占位符
              String sql = "select * from user_login where loginName=? and loginPwd=?;";
              pst = conn.prepareStatement(sql);
              pst.setString(1,map.get("user"));
              pst.setString(2,map.get("pwd"));
              rs = pst.executeQuery();
//            rs = stat.executeQuery(sql); 不安全
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(rs !=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            /*if(stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }*/
            if(pst != null) {
                try {
                    pst.close();
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

    /**
     * 用户登录界面
     * 返回账号密码
     * @return
     */
    public static Map initUI() {
        Scanner sc = new Scanner(System.in);
        System.out.println("用户名：");
        String user = sc.next();
        System.out.println("密码：");
        String pwd = sc.next();
        Map<String, String> map = new HashMap<>();
        map.put("user",user);
        map.put("pwd",pwd);
        return map;
    }
}
