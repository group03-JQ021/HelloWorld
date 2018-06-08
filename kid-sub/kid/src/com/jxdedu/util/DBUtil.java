package com.jxdedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 获取数据库连接，关闭资源
 * 
 * @Title: DBUtil.java
 * @Description:
 * @author: xie jing
 * @date: 2018-5-10
 */
public class DBUtil {
	// 连接数据库的四大参数
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String username = "test";
	private static String pwd = "test";

	/**
	 * 获取数据库连接
	 * 
	 * @Description:@return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);// 把驱动加载到内存中
			conn = DriverManager.getConnection(url, username, pwd);// 获取连接
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭资源
	 * 
	 * @Description:@param conn 数据库连接
	 * @Description:@param st 执行sql的对象
	 * @Description:@param rs 执行结果
	 */
	public static void closeAll(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {// 如果结果集非空，关闭资源
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (st != null) {// 执行sql对象非空，关闭资源
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if (conn != null){// 连接对象非空，关闭资源
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
