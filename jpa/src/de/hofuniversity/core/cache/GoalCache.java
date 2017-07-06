package de.hofuniversity.core.cache;

import de.hofuniversity.core.Goal;

/**
 * 
 * @author Markus Exner
 *
 */

public class GoalCache extends Cache<Goal> {
    private static GoalCache INSTANCE;
    
    private GoalCache() {}
    
    public static GoalCache getInstance() {
	if (INSTANCE == null) {
	    	INSTANCE = new GoalCache();
	}
        return INSTANCE;
    } 
}
