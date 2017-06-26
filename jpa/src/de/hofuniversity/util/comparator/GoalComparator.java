package de.hofuniversity.util.comparator;

import java.util.Comparator;

import de.hofuniversity.core.Goal;

public class GoalComparator implements Comparator<Goal> {

    @Override
    public int compare(Goal o1, Goal o2) {
	int o1Sum = o1.getPointsHome() + o1.getPointsGuest();
	int o2Sum = o2.getPointsHome() + o2.getPointsGuest();

	if (o1Sum > o2Sum) {
	    return 1;
	}

	if (o1Sum < o2Sum) {
	    return -1;
	}
	return 0;
    }

}
