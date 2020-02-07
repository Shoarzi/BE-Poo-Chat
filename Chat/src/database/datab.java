package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class datab {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tpservlet_05";

	// Database credentials
	static final String USER = "tpservlet_05";
	static final String PASS = "uinohG6e";

	static Connection conn = null;
	static java.sql.Statement stmt = null;
	static String sql = null;

	public static void create_table(String id1, String id2) {
		try {
			// Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// : Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			stmt = conn.createStatement();

			// Create List User Table
			String i = id1 + id2;
			String sqltest = "CREATE TABLE History" + i + " (" + "uid VARCHAR(45) NOT NULL, "
					+ "other_uid VARCHAR(45) NOT NULL," + "direct_sense BOOLEAN,"
					+ "date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " + "line_text TEXT NOT NULL ,"
					+ "PRIMARY KEY (uid,other_uid,date));";
			stmt.executeUpdate(sqltest);
			System.out.println("History message Table created");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}

	public static boolean TableExists(String id1, String id2) {
		java.sql.DatabaseMetaData md;
		boolean f = false;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			// Id of the table = Ids of both chatters ;
			while (rs.next()) {
				if (rs.getString(3).contentEquals("History" + id1 + id2)) {
					f = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static void Historise(String id1, String id2, Boolean directSense, String message) {
		LocalDateTime now = LocalDateTime.now();
		String tablename = "History" + id1 + id2;
		try {
			if (!TableExists(id1, id2)) {
				create_table(id1, id2);
			}
			stmt = conn.createStatement();
			
			//Boolean to int
			int b = 0;
			if (directSense)
				b = 1;
			sql = "INSERT INTO " + tablename + " VALUES ('" + id1 + "','" + id2 + "','" + b + "','" + now
					+ "','" + message + "');";
			stmt.executeUpdate(sql);
			System.out.println("History message Table updated " + now);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}