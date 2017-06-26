/**
 * 
 */
package de.hofuniversity.io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.hofuniversity.core.Team;

/**
 * @author Markus Exner
 *
 */
public class DatabaseTeamWriter {
	
	private static String TEAM_INSERT_STATEMENT = "INSERT INTO t_team ( c_name, c_icon_url, c_stadium_id ) VALUES ( ?, ? , ? )";
	
	private Database db;
	private DatabasePlayerWriter dbPlayerWriter;

	public DatabaseTeamWriter(Database db) {
		if (db == null) {
			throw new IllegalArgumentException("Cannot connect to a null database");
		}
		this.db = db;
	}
	
    public void writeTeam(Team team) throws Exception {
    	if (team == null) { throw new IllegalArgumentException("Cannot write a NULL team."); }
        	
    	if (this.dbPlayerWriter == null) {
    		this.dbPlayerWriter = new DatabasePlayerWriter(this.db);
    	}
    	
	PreparedStatement stmt = db.getConnection("soccer").prepareStatement(TEAM_INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
	
	stmt.setString(1, team.getName());
	stmt.setString(2, team.getIconURL());
	stmt.setInt(3, team.getStadium().getId());
	
	stmt.executeUpdate();
	
	ResultSet keyResultSet = stmt.getGeneratedKeys();
	if (keyResultSet.next()) { team.setId((int) keyResultSet.getInt(1)); }
	
	stmt.close();
	stmt = null;
	this.db.closeConnection();
    }	
}
