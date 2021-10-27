package com.test.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://10.44.166.76:3306/mechine?useUnicode=true&characterEncoding=utf-8";
	private final static String USERNAME = "admin";// 数据库登录用户名
	private final static String PASSWORD = "admin123";// 数据登录密码

	private static Connection conn;

	public static Connection getConn() throws Exception {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 连接数据库
		} catch (Exception e) {
			throw new Exception("数据库连接异常！");
		}
		return conn;// 返回连接对象
	}

	// 关闭数据库连接
	public void CloseConnection() {
		if (null != conn) {
			try {
				conn.close();// 关闭连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
