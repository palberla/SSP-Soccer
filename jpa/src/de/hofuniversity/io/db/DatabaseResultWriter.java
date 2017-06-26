package de.hofuniversity.io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hofuniversity.core.Result;

public class DatabaseResultWriter {
    
    private static String RESULT_INSERT_STATEMENT = "INSERT INTO t_result (c_points_home, c_points_guest) VALUES (? , ?)";
    
    private Database db;
    
    public DatabaseResultWriter(Database db) {
	if (db == null) {
	    throw new IllegalArgumentException("Can not connect to NULL database");
	}
	this.db = db;
    }
    
    public void writeResult(Result result) throws SQLException, Exception {
	if (result == null) {
	    throw new IllegalArgumentException("Can not write NULL as a result");
	}
	
	PreparedStatement stmt = db.getConnection("soccer").prepareStatement(RESULT_INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
	
	stmt.setInt(1, result.getPointsHome());
	stmt.setInt(2, result.getPointsGuest());
	
	stmt.executeUpdate();
	
	ResultSet keyResultSet = stmt.getGeneratedKeys();
	if (keyResultSet.next()) { result.setId((int) keyResultSet.getInt(1)); }
	
	stmt.close();
	stmt = null;
	this.db.closeConnection();
    }
}
