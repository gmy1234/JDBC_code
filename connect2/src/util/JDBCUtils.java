package util;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * 数据库的连接和关闭
 */
public class JDBCUtils {

    /**
     * @deprecated 获取数据库的连接
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        // 1. 读取配置文件中的4个信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driverClass = pro.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * @deprecated 用于关闭资源
     */
    public static  void closeResource(Connection connection , Statement statement){

        try {
            if (statement!=null)
                statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection!=null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection connection , Statement statement,
                                     ResultSet resultSet){
        try {
            if (statement!=null)
                statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection!=null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
