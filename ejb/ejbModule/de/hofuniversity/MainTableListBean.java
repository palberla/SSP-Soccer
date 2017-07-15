package de.hofuniversity;

import java.util.Collection;

import de.hofuniversity.ejbbean.bean.impl.TableListBean;
import de.hofuniversity.ejbbean.data.TeamGroupSummaryData;

/**
 * 
 * @author Michael Jahn
 *
 */

public class MainTableListBean {

    public static void main(String[] args) {
	TableListBean teamBean = new TableListBean();
//	System.out.println(teamBean.getTeamPlayedMatches(1).size());
//	for (Match match : teamBean.getTeamMatches(1))
//	for (Match match : teamBean.getTeamPlayedMatches(1))
//	{
//	    System.out.println(match.getHomeTeam().getName() 
//		    + " " + ((match.getFinalScore() != null) ? match.getFinalScore().getPointsHome() : "-") + " : "
//		    + " " + ((match.getFinalScore() != null) ? match.getFinalScore().getPointsGuest() : "-")
//		    + " " + match.getGuestTeam().getName());
//	}
//	TeamGroupSummaryData teamGroupSummaryData = teamBean.getTeamGroupSummaryData(1);
//	System.out.println(
//			"Verein:	" + teamGroupSummaryData.getTeam().getName() + "\n" +
//			"Icon:		" + teamGroupSummaryData.getTeam().getIconURL() + "\n" +
//			"Spieleanzahl:	" + teamGroupSummaryData.getGameAmount() + "\n" +
//			"Punkte:	" + teamGroupSummaryData.getPoints() + "\n" +
//			"+Tore:		" + teamGroupSummaryData.getGoalPlus() + "\n" +
//			"-Tore:		" + teamGroupSummaryData.getGoalMinus() + "\n" +
//			"Tordifferenz:	" + (teamGroupSummaryData.getGoalPlus() - teamGroupSummaryData.getGoalMinus())
//		
//		);
	Collection<TeamGroupSummaryData> teamTableList = teamBean.getTableList();
	int index = 0;
	System.out.printf("%5s\t%35s\t%5s\t%5s\t%5s\t%5s","Platz", "Name", "Spiele", "+Tore", "-Tore", "Punkte");
	for (TeamGroupSummaryData tgsd : teamTableList)
	{
	    printlnTeamForPlace(tgsd, ++index);
	}
    }
    
    public static void printlnTeamForPlace(TeamGroupSummaryData tsgd, int index)
    {
	System.out.printf("%5d\t%35s\t%5d\t%5d\t%5d\t%5d", index, tsgd.getTeamName(), tsgd.getGameAmount(),
		tsgd.getGoalPlus(), tsgd.getGoalMinus(), tsgd.getPoints());
	System.out.println();
//	System.out.println(
//		index + "\t" + tsgd.getTeam().getName() + "\t\t" + 
//			tsgd.getGameAmount() + "\t" +
//			tsgd.getGoalPlus() + "\t" +
//			tsgd.getGoalMinus() + "\t" +
//			tsgd.getPoints()
//	);
    }

}
