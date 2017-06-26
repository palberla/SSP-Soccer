/**
 * 
 */
package de.hofuniversity.io.xml;

import org.jdom2.Element;

import de.hofuniversity.core.Player;
import de.hofuniversity.core.cache.PlayerCache;

/**
 * @author Michael Jahn
 *
 */
public class XMLTeamPlayerReader
{
	public XMLTeamPlayerReader() {}
	
	public Player readPlayer(Element playerElement)
	{
		if (playerElement == null) { throw new IllegalArgumentException("Cannot read NULL element for player."); }
		
		Player player = new Player();
		
		player.setName(playerElement.getText());
		
		PlayerCache.getInstance().add(player);
		
		return player;
	}
}
