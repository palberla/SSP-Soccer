package de.hofuniversity.io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hofuniversity.core.Goal;

/**
 * 
 * @author Michael Jahn
 *
 */

public class DatabaseGoalWriter {
    
    private static String GOAL_INSERT_STATEMENT = "INSERT INTO t_goal "
    	+ "(c_points_home, c_points_guest, c_minute, c_player_id, c_match_id,c_penalty, c_own, c_overtime, c_comment)"
    	+ "						VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private Database db;
    
    public DatabaseGoalWriter(Database db) {
	if (db == null) {
	    throw new IllegalArgumentException("Can not connect to NULL database");
	}
	this.db = db;
    }
    
    public void writeGoal(Goal goal) throws SQLException, Exception {
	if (goal == null) { throw new IllegalArgumentException("Cannot write a NULL as a goal."); }
	
	PreparedStatement stmt = db.getConnection("soccer").prepareStatement(GOAL_INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
	
	stmt.setInt(1, goal.getPointsHome());
	stmt.setInt(2, goal.getPointsGuest());
	stmt.setInt(3, goal.getMinute());
	stmt.setInt(4, goal.getPlayer().getId());
	stmt.setInt(5, goal.getMatch().getId());
	stmt.setBoolean(6, goal.isPenaltyGoal());
	stmt.setBoolean(7, goal.isOwnGoal());
	stmt.setBoolean(8, goal.isOvertimeGoal());
	stmt.setString(9, goal.getComment());
	
	stmt.executeUpdate();
	
	ResultSet keyResultSet = stmt.getGeneratedKeys();
	if (keyResultSet.next()) { goal.setId((int) keyResultSet.getInt(1)); }
	
	stmt.close();
	stmt = null;
	this.db.closeConnection();
    }

}
