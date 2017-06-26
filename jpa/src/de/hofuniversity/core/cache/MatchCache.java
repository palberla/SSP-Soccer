package de.hofuniversity.core.cache;

import de.hofuniversity.core.Match;

public class MatchCache extends Cache<Match> {
    
    private static MatchCache INSTANCE;
    
    private MatchCache() {}
    
    public static MatchCache getInstance() {
	if (INSTANCE == null) {
	    	INSTANCE = new MatchCache();
	}
        return INSTANCE;
    }       
}
