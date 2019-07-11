package util.db.jdbc;

import java.sql.*;

/**
 * @Description //
 * @Author yz
 * @Date 2019-7-10
 * @Vesion 1.0
 **/
public class JdbcConfig {
    private String driver; // 驱动
    private String url; // 数据库地址
    private String user; // 用户
    private String pass; // 密码

    private Connection connection;

    public JdbcConfig(String driver, String url, String user, String pass) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pass = pass;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if(connection != null) {
            return connection;
        }
        return connection = DriverManager.getConnection(url, user, pass);
    }

    //开启事务
    public void beginTransaction(Connection connection) {
        if (connection != null) {
            try {
                if (connection.getAutoCommit()) {
                    connection.setAutoCommit(false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //提交事务
    public void commitTransaction(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //回滚事务
    public void rollBackTransaction(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭连接
    public void close(Object o){
        if (o == null){
            return;
        }
        if (o instanceof ResultSet){
            try {
                ((ResultSet)o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(o instanceof Statement){
            try {
                ((Statement)o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Connection){
            Connection c = (Connection)o;
            try {
                if (!c.isClosed()){
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //重载关闭连接
    public void close(ResultSet rs, Statement stmt, Connection conn){
        close(rs);
        close(stmt);
        close(conn);
    }
    //重载关闭连接
    public void close(ResultSet rs, Connection conn){
        close(rs);
        close(conn);
    }
    //重载关闭连接
    public void close(ResultSet rs, PreparedStatement ps, Connection conn){
        close(rs);
        close(ps);
        close(conn);
    }
}
