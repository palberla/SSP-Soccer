package de.hofuniversity.io.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Michael Jahn, Markus Exner
 *
 */
public class Database {
	
	public Database() {}

	private Connection connection;

	public Connection getConnection(String dbname) throws Exception {
		if (this.getConnection() == null) {
			this.newConnection(dbname);
		}

		return this.connection;
	}
	
	private Connection getConnection()
	{
		return this.connection;
	}
	
	private void newConnection(String dbname) throws Exception
	{
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver")
				.newInstance();
		DriverManager.registerDriver(driver);
		this.connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/" + dbname, "root", "");
	}

	public void createSchema() throws Exception {
		Statement stmt = this.getConnection("mysql").createStatement();
		for (String sql : getCreateDDL()) {
			if (sql.length() > 10) {
				stmt.execute(sql);
			}
		}
		stmt.close();
		this.closeConnection();
	}

	private String[] getCreateDDL() throws IOException {
		InputStream in = getClass().getResourceAsStream("soccer.sql");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line, sql = "";
		while ((line = br.readLine()) != null) {
			sql += line + "\n";
		}
		br.close();

		return sql.split("[;]");
	}
	
	public int getLastAutoId() throws Exception
	{
		Connection connection = this.getConnection();
		if (connection == null) { return -2; }
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery("Select last_INSERT_ID()");
		int id = res.next() ? res.getInt(1) : -1;
		stmt.close();
		return id;
	}

	public void closeConnection() throws SQLException
	{
		if (this.connection != null)
		{
		    this.connection.close();
		    this.connection = null;
		}
	}

}
