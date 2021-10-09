package com.test.generator;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CreUpSql {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] noUpKeys = { "taskNo", "appName" };
		String[][] contions = { { "taskNo =", "appName =" } };
		new CreUpSql().createUpdateSql("test_worksheet", noUpKeys, contions);
	}

	/**
	 * 生成更新sql语句
	 * 
	 * @param tableName 表名
	 * @param noUpKeys  不被更新的字段
	 * @param contions  where条件 [0]条件值的键 【1】条件名字的键+条件标志 如： like = 等
	 */
	public void createUpdateSql(String tableName, String[] noUpKeys, String[][] contions) {
		String fileName = "D:" + File.separator + "sqlwork" + File.separator + tableName + "UPDATE" + ".txt";

		String[][] strs = getFieldsNames(tableName);

		checkArrNull(strs);

		File f = createNewFile(fileName);

		createUpFile(f, strs, noUpKeys, tableName, contions);
	}

	/**
	 * 检查str有没有在数组values中 检查不通过的话返回ture
	 * 
	 * @return
	 */
	public boolean check(String str, String[] values) {
		boolean flag = false;
		for (int i = 0; i < values.length; i++) {
			if (str.equals(values[i])) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 添加修改sql文件内容
	 * 
	 * @param f
	 * @param strs
	 * @param tableName
	 */
	private void createUpFile(File f, String[][] strs, String[] noUpKeys, String tableName, String[][] contions) {

		OutputStream out;
		try {
			out = new FileOutputStream(f, true);

			String str = " UPDATE " + tableName + " ";
			writeStrToFile(out, f, str);

			writeStrToFile(out, f, "<set>");
			// *******************添加SET***************************************************************************
			for (int i = 0; i < strs.length; i++) {
				if (noUpKeys != null) {
					if (check(strs[i][0], noUpKeys)) {
						continue;
					}
				}
				String temp = "#{" + strs[i][0] + "}";
				if (strs[i][1].startsWith("DATE") || strs[i][1].startsWith("TIME")) {
					temp = "to_date(" + temp + ",'yyyy-mm-dd hh24:mi:ss')";
				}
				String nullstr = strs[i][0] + "=null";
				if (i == (strs.length - 1)) {
				} else {
					temp += ",";
					nullstr += ",";
				}
				temp = "<when test=\" " + strs[i][0] + "!=null  \">" + strs[i][0] + "=" + temp + "</when>";
				// upNullFlag默认为空 （全部更新） upNullFlag如果为false 则不更新VO中为空的值
				temp = temp + "<when test=\" " + strs[i][0] + "==null and  upNullFlag=='true' \">" + nullstr
						+ "</when>";
				temp = "\t<choose>" + temp + "</choose>";

				writeStrToFile(out, f, temp);
			}
			writeStrToFile(out, f, "</set>");
			if (contions != null && contions.length > 0) {
				creatWhere(out, f, contions);
			}
			out.close();
			System.out.println(tableName + "修改语句生成成功-->" + f.toString());
			Desktop.getDesktop().open(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param out
	 * @param f
	 * @param contions [0]条件值的键 【1】条件名字的键+条件标志 如： like = 等
	 */
	private void creatWhere(OutputStream out, File f, String[][] contions) {
		// TODO Auto-generated method stub
		writeStrToFile(out, f, "<where>");

		for (int i = 0; i < contions.length; i++) {
			String temp = "\tand " + contions[i][1] + " #{" + contions[i][0] + "}";
			writeStrToFile(out, f, temp);
		}
		writeStrToFile(out, f, "</where>");
	}

	/**
	 * 将字符串写到指定文件中
	 * 
	 * @param out
	 * @param f
	 * @param str
	 */
	private void writeStrToFile(OutputStream out, File f, String str) {
		// TODO Auto-generated method stub
		byte[] b = ("\r\n\t\t" + str).getBytes();
		for (int i = 0; i < b.length; i++) {
			try {
				out.write(b[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * 检查数组是否为空
	 * 
	 * @param strs
	 */
	private void checkArrNull(String[][] strs) {
		if (strs == null || strs.length == 0) {
			try {
				throw new Exception("不存在的表名");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * 根据文件名称创建一个新文件并返回
	 * 
	 * @param fileName
	 * @return
	 */
	public File createNewFile(String fileName) {
		File f = new File(fileName);

		try {
			if (f.exists()) { // 如果文件存在则删除
				f.delete();
			}
			f.createNewFile();// 创建sql文件
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return f;
	}

	/**
	 * 获取表的所有字段名称
	 * 
	 * @param tabname
	 * @return
	 */
	public String[][] getFieldsNames(String tabname) {

		Connection conn = null;
		try {
			conn = ConnUtil.getConn();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Statement stat = null;
		ResultSet rs = null;
		ResultSetMetaData data = null; // 定义ResultSetMetaData对象
		String[][] resultStrs = null;
		int coloumCount = 0;
		try {
			stat = conn.createStatement();
			String sql = "select * from " + tabname;
			rs = stat.executeQuery(sql);// 查询数据
			data = rs.getMetaData();
			coloumCount = data.getColumnCount();
			resultStrs = new String[coloumCount][2];
			for (int i = 0; i < coloumCount; i++) {
				resultStrs[i][0] = data.getColumnName(i + 1);
				resultStrs[i][1] = data.getColumnTypeName(i + 1);
			}

			// 关闭数据库资源
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultStrs;
	}
}