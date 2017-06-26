/**
 * 
 */
package de.hofuniversity.io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import de.hofuniversity.core.Stadium;

/**
 * @author Michael Jahn
 *
 */
//
//CREATE TABLE `soccer`.`t_stadium` (
//	  `c_id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
//	  `c_name` VARCHAR(35) NOT NULL,
//	  `c_capacity` INT UNSIGNED NOT NULL,
//	  `c_address` VARCHAR(120) NOT NULL,
//	  `c_city` VARCHAR(35) NOT NULL,
//	  `c_picture_outside_url` VARCHAR(120) NOT NULL,
//	  `c_picture_inside_url` VARCHAR(120) NOT NULL,
//	  `c_longitude` DOUBLE NOT NULL,
//	  `c_latitude` DOUBLE NOT NULL,



public class DatabaseStadiumWriter
{
	private static String STADIUM_INSERT_STATEMENT = "INSERT INTO t_stadium ( c_name, c_capacity, c_address, c_city,"
		+ "c_picture_outside_url, c_picture_inside_url, c_longitude, c_latitude ) "
		+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
	
	private Database db;
	
	/**
	 * 
	 */
	public DatabaseStadiumWriter(Database db)
	{
		if (db == null) {
			throw new IllegalArgumentException("Cannot connect to a NULL database");
		}
		this.db = db;
	}
	
	public void writeStadium(Stadium stadium) throws Exception
	{
		if (stadium == null) { throw new IllegalArgumentException("Cannot write a NULL stadium."); }
		
		PreparedStatement stmt = db.getConnection("soccer").prepareStatement(STADIUM_INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
		
		stmt.setString(1, stadium.getName());
		stmt.setInt(2, stadium.getViewers());
		stmt.setString(3, stadium.getAddress());
		stmt.setString(4, stadium.getCity());
		stmt.setString(5,  stadium.getImageOutside());
		stmt.setString(6,  stadium.getImageInside());
		stmt.setDouble(7, stadium.getGeologicalCoordinates().getLongitude());
		stmt.setDouble(8, stadium.getGeologicalCoordinates().getLatitude());
		
		stmt.executeUpdate();
		
		ResultSet keyResultSet = stmt.getGeneratedKeys();
		if (keyResultSet.next()) { stadium.setId((int) keyResultSet.getInt(1)); }
		
		stmt.close();
		stmt = null;
		this.db.closeConnection();
	}
}
