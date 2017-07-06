package de.hofuniversity.ejbbean.data.impl;

import de.hofuniversity.ejbbean.data.MatchDetailsGoalSummaryData;

/**
 * 
 * @author Michael Jahn
 *
 */

public class DefaultMatchDetailsGoalSummaryData implements MatchDetailsGoalSummaryData {
    
    private int homeScore, guestScore;
    private String comment, goalGetterName;
    
    public DefaultMatchDetailsGoalSummaryData() {}
    
    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGoalGetterName(String goalGetterName) {
        this.goalGetterName = goalGetterName;
    }

    @Override
    public int getHomeScore() {
	return this.homeScore;
    }

    @Override
    public int getGuestScore() {
	return this.guestScore;
    }

    @Override
    public String getComment() {
	if (this.comment == null) return "";
	return this.comment;
    }

    @Override
    public String getGoalGetterName() {
	return this.goalGetterName;
    }
    
}
