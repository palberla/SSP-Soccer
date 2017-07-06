package de.hofuniversity;

import de.hofuniversity.ejbbean.bean.impl.TeamDetailsBean;
import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;
import de.hofuniversity.ejbbean.data.TeamDetailsSummaryData;

/**
 * 
 * @author Michael Jahn
 *
 */

public class MainTeamDetailsBean {

    public static void main(String[] args) {

	TeamDetailsBean tdb = new TeamDetailsBean();
	TeamDetailsSummaryData tdsd = tdb.getTeamDetails(1);

	System.out.printf("Team: %s", tdsd.getTeamName()); System.out.println();
	System.out.printf("Icon: %s", tdsd.getTeamIconURL()); System.out.println();
	System.out.printf("Stadium: %s", tdsd.getStadiumName()); System.out.println();
	System.out.printf("%s", tdsd.getStadiumAdress()); System.out.println();
	System.out.printf("%d", tdsd.getStadiumCapacity()); System.out.println();
	System.out.printf("%s", tdsd.getStadiumInsideURL()); System.out.println();
	System.out.printf("%s", tdsd.getStadiumOutsideURL()); System.out.println();
	System.out.printf("%1s, %2s", tdsd.getLatitude(), tdsd.getLongitude()); System.out.println();

	System.out.printf("%30s\t%20s\t%30s", "Heimmannschaft", "Ergebnis", "Gastmannschaft");
	System.out.println();
	for (MatchGroupSummaryData mgsd : tdsd.getTeamMatches()) {
	    printlnMatchForGroup(mgsd);
	}
	System.out.println("Spieler:");
	for (String playerName : tdsd.getTeamPlayerList())
	{
	    System.out.println(playerName);
	}
    }

    private static void printlnMatchForGroup(MatchGroupSummaryData mgsd) {
	System.out.printf("%30s\t%20s\t%30s", mgsd.getHomeTeamName(), (mgsd.getHomeTeamFinalPoints() + ":" + mgsd.getGuestTeamFinalPoints()
		+ " (" + mgsd.getHomeTeamHalfPoints() + ":" + mgsd.getGuestTeamHalfPoints() + ")"), mgsd.getGuestTeamName());
	System.out.println();
    }
}
