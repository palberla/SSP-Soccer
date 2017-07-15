package de.hofuniversity.core.cache;

import de.hofuniversity.core.Result;

/**
 * 
 * @author Markus Exner
 *
 */

public class ResultCache extends Cache<Result> {
    
    private static ResultCache INSTANCE;
    
    private ResultCache() {}
    
    public static ResultCache getInstance() {
	if (INSTANCE == null) {
	    	INSTANCE = new ResultCache();
	}
        return INSTANCE;
    } 
}
