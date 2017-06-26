package de.hofuniversity.io.xml;

import org.jdom2.Element;

import de.hofuniversity.core.Player;
import de.hofuniversity.io.xml.util.PlayerNameNormalizer;

public class XMLMatchGoalPlayerReader {
    
    private PlayerNameNormalizer playerNameNormalizer;

    public XMLMatchGoalPlayerReader() {
    }

    public Player readPlayer(Element playerElement) {
	if (playerElement == null) {
	    throw new IllegalArgumentException("Cannot read NULL element for player.");
	}
	
	if (playerNameNormalizer == null) {
	    playerNameNormalizer = new PlayerNameNormalizer();
	}
	
	String playername = this.playerNameNormalizer.getNormalizedPlayerName(playerElement.getChildText("goalGetterName"));
	
	Player player = new Player();
	player.setName(playername);

	return player;
    }
}
