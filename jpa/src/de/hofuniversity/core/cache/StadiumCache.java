package de.hofuniversity.core.cache;

import de.hofuniversity.core.Stadium;

public class StadiumCache extends Cache<Stadium> {
    
    private static StadiumCache INSTANCE;
    
    private StadiumCache() {}
    
    public static StadiumCache getInstance() {
	if (INSTANCE == null) {
	    INSTANCE = new StadiumCache();
	}
	return INSTANCE;
    }
}
