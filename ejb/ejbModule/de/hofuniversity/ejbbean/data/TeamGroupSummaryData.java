package de.hofuniversity.ejbbean.data;

/**
 * 
 * @author Michael Jahn
 *
 */

public interface TeamGroupSummaryData {

    public int getPoints();

    public int getGoalPlus();

    public int getGoalMinus();

    public String getTeamIconUrl();
    
    public String getTeamName();

    public int getGameAmount();
}
