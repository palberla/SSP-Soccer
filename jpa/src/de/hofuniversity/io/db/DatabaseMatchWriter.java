package de.hofuniversity.io.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import de.hofuniversity.core.Match;

public class DatabaseMatchWriter {
    
    private static String MATCH_INSERT_STATEMENT = "INSERT INTO t_match (c_group_id, c_home_team_id, c_guest_team_id, c_stadium_id, c_half_result_id, c_final_result_id, c_date, c_viewers) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private Database db;
    
    public DatabaseMatchWriter(Database db) {
	if (db == null) {
	    throw new IllegalArgumentException("Can not connect to NULL database");
	}
	this.db = db;
    }
    
    public void writeMatch(Match match) throws SQLException, Exception{
	if (match == null) { throw new IllegalArgumentException("Cannot write a NULL as a match."); }
	
	PreparedStatement stmt = db.getConnection("soccer").prepareStatement(MATCH_INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
	
	stmt.setInt(1, match.getGroupId());
	stmt.setInt(2,  match.getHomeTeam().getId());
	stmt.setInt(3,  match.getGuestTeam().getId());
	stmt.setInt(4, match.getStadium().getId());
	if (match.getHalfScore() != null)
	{
	    stmt.setInt(5,  match.getHalfScore().getId());
	}
	else
	{
	    stmt.setNull(5, Types.SMALLINT);
	}
	if (match.getFinalScore() != null)
	{
	    stmt.setInt(6,  match.getFinalScore().getId());
	}
	else
	{
	    stmt.setNull(6, Types.SMALLINT);
	}
	stmt.setDate(7, new Date(match.getCalendar().getTime().getTime()));
	stmt.setInt(8,  match.getViewers());
	
	stmt.executeUpdate();
	
	ResultSet keyResultSet = stmt.getGeneratedKeys();
	if (keyResultSet.next()) { match.setId((int) keyResultSet.getInt(1)); }
	
	stmt.close();
	stmt = null;
	this.db.closeConnection();
    }
}
