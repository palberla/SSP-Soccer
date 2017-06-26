/**
 * 
 */
package de.hofuniversity.io.xml.util;

/**
 * @author Michael Jahn
 *
 */
public class PlayerNameEqualizer {
    /**
     * 
     */
    public PlayerNameEqualizer() {
    }

    public boolean equalsName(String playerName0, String playerName1) {
	if (playerName0 == null) {
	    throw new IllegalArgumentException("Cannot equals a NULL name string.");
	}
	playerName0 = playerName0.trim();
	if (playerName0.isEmpty()) {
	    throw new IllegalArgumentException("Cannot equals an empty name string.");
	}
	if (playerName1 == null) {
	    throw new IllegalArgumentException("Cannot equals to a NULL name string.");
	}
	playerName1 = playerName1.trim();
	if (playerName1.isEmpty()) {
	    throw new IllegalArgumentException("Cannot equals to an empty name string.");
	}

//	String[] playerName0StringArray = playerName0.split(" ");
//	String[] playerName1StringArray = playerName1.split(" ");
//
//	String playerName0String = playerName0StringArray[playerName0StringArray.length - 1];
//	String playerName1String = playerName1StringArray[playerName1StringArray.length - 1];
//
//	return playerName0String.equalsIgnoreCase(playerName1String);
	
	return playerName0.equalsIgnoreCase(playerName1);
    }
}
