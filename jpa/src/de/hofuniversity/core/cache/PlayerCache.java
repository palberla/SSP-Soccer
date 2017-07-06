package de.hofuniversity.core.cache;

import de.hofuniversity.core.Player;
import de.hofuniversity.io.xml.util.PlayerNameEqualizer;

/**
 * 
 * @author Markus Exner
 *
 */

public class PlayerCache extends Cache<Player> {
    private static PlayerCache INSTANCE;
    private static PlayerNameEqualizer nameEqualizer;
    
    private Player cachedPlayer = null;

    private PlayerCache() {}

    public static PlayerCache getInstance() {
	if (INSTANCE == null) {
	    INSTANCE = new PlayerCache();
	}
	return INSTANCE;
    }

    private Player getPlayer(String name) {
	if (PlayerCache.nameEqualizer == null)
	{
	    PlayerCache.nameEqualizer = new PlayerNameEqualizer();
	}
	for (Player player : this.getCollection()) {
	    if (PlayerCache.nameEqualizer.equalsName(player.getName(), name)) {
		return player;
	    }
	}
	return null;
    }

    public boolean contains(String name) {
	Player player = this.getPlayer(name);
	if (player != null) {
	    this.cachedPlayer = player;
	    return true;
	}
	return false;
    }

    public Player get(String name) {
	if (this.cachedPlayer != null && PlayerCache.nameEqualizer.equalsName(cachedPlayer.getName(), name)) {
	    return this.cachedPlayer;
	}
	Player player = this.getPlayer(name);
	if (player == null) {
	    throw new IllegalArgumentException("No player with name " + name + " found in cache");
	}
	return player;
    }
}
