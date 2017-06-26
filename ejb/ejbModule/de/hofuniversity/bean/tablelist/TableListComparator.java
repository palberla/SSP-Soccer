package de.hofuniversity.bean.tablelist;

import java.util.Comparator;

public class TableListComparator implements Comparator<TeamGroupSummaryData> {

    @Override
    public int compare(TeamGroupSummaryData tgsd0, TeamGroupSummaryData tgsd1) {
	if (tgsd1.getPoints() < tgsd0.getPoints()) { return -1; }
	if (tgsd1.getPoints() > tgsd0.getPoints()) { return 1; }
	
	int tgsd0Difference = tgsd0.getGoalPlus() - tgsd0.getGoalMinus();
	int tgsd1Difference = tgsd1.getGoalPlus() - tgsd1.getGoalMinus();
	
	if (tgsd1Difference < tgsd0Difference) { return -1; }
	if (tgsd1Difference > tgsd0Difference) { return 1; }
	
	// Direkter Vergleich
	// Siege
	// Name
	
	return 0;
    }

}