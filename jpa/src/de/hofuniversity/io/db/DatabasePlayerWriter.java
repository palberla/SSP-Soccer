/**
 * 
 */
package de.hofuniversity.io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.hofuniversity.core.Player;

/**
 * @author Markus Exner
 *
 */
public class DatabasePlayerWriter {
	
	private static String PLAYER_INSERT_STATEMENT = "INSERT INTO t_player ( c_name, c_team_id ) VALUES ( ?, ? )";
	
	private Database db;
	
	public DatabasePlayerWriter(Database db) {
		if (db == null) {
			throw new IllegalArgumentException("Cannot connect to a null database");
		}
		this.db = db;
	}
	
	public void writePlayer(Player player) throws Exception {
		if (player == null) { throw new IllegalArgumentException("Cannot write a NULL player."); }
		
		PreparedStatement stmt = db.getConnection("soccer").prepareStatement(PLAYER_INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, player.getName());
		stmt.setInt(2, player.getTeam().getId());
		
		stmt.executeUpdate();
		
		ResultSet keyResultSet = stmt.getGeneratedKeys();
		if (keyResultSet.next()) { player.setId((int) keyResultSet.getInt(1)); }
		
		stmt.close();
		stmt = null;
		this.db.closeConnection();
	}
}
