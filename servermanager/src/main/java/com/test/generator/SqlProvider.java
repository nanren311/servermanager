package com.test.generator;

public class SqlProvider {

	public static String upperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	static void update(String tabname) {
		CreUpSql sql = new CreUpSql();
		String[][] string = sql.getFieldsNames(tabname);
		for (int i = 0; i < string.length; i++) {

			String parm = string[i][0];
			System.out.println("  if(sheet.get" + upperCase(parm) + "() != null){SET(\"" + parm + "='\"+sheet.get"
					+ upperCase(parm) + "()+\"'\"); }");
		}
	}

	public static void main(String[] args) {
		update("test_article");
	}
}
