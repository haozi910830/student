package utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUTILE {
	//SQL SERVER JDBC DRIVER
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	//SQL SERVER URL
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/studentserver?characterEncoding=UTF-8";

	//SQL SERVER USER_NAME
	private static final String USER_NAME = "root";

	//SQL SERVER USER_PASS
	private static final String USER_PASS = "123123";

	//SQL SERVER Connection
		private static Connection con = null;

		//SQL SERVER PreparedStatement
		private static PreparedStatement pst = null;

		//SQL SERVER ResultSet
		private static ResultSet rst = null;

		/**
		 * INIT DATA_BASE
		 *
		 */
		private static void dataBaseInit(String SQL,Object...objects) throws Exception{
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER_NAME,USER_PASS);
			pst = con.prepareStatement(SQL);
			for(int i = 0; i < objects.length; i++) {
				pst.setObject(i + 1, objects[i]);
			}
		}

		/**
		 * INSERT UPDATE DELETE
		 * API
		 *
		 */
		public static boolean upDataBaseTable(String SQL,Object...objects) throws Exception{
			boolean bo = false;
			dataBaseInit(SQL,objects);
			bo = pst.executeUpdate() > 0? true:false;
			dataBaseClose();
			return bo;
		}

		/**
		 * SELECT
		 *
		 */
		public static ResultSet selectDataBaseTable(String SQL,Object...objects) throws Exception{
			dataBaseInit(SQL,objects);
			rst = pst.executeQuery();
			return rst;
		}

		/**
		 * DateBase CLOSE
		 * PRIVATE
		 *
		 */
		private static void dataBaseClose() throws Exception{
			if(rst != null) {
				rst.close();
				rst = null;
			}
			if(pst != null) {
				pst.close();
				pst =null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		}

		/**
		 * DataBase CLOSE
		 * PUBLIC
		 *
		 */
		public static void dataBaseClose(ResultSet rst,PreparedStatement pst,Connection con)throws Exception{
			if(rst != null) {
				rst.close();
				rst = null;
			}
			if(pst != null) {
				pst.close();
				pst =null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		}
	}





