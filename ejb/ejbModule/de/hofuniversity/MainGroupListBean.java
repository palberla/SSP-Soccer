package de.hofuniversity;

import java.util.Collection;

import de.hofuniversity.ejbbean.bean.impl.GroupListBean;
import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;

public class MainGroupListBean {

    public static void main(String[] args) {
	GroupListBean groupListBean = new GroupListBean();
	// System.out.println(teamBean.getTeamPlayedMatches(1).size());
	// for (Match match : teamBean.getTeamMatches(1))
	// for (Match match : teamBean.getTeamPlayedMatches(1))
	// {
	// System.out.println(match.getHomeTeam().getName()
	// + " " + ((match.getFinalScore() != null) ?
	// match.getFinalScore().getPointsHome() : "-") + " : "
	// + " " + ((match.getFinalScore() != null) ?
	// match.getFinalScore().getPointsGuest() : "-")
	// + " " + match.getGuestTeam().getName());
	// }
	// TeamGroupSummaryData teamGroupSummaryData =
	// teamBean.getTeamGroupSummaryData(1);
	// System.out.println(
	// "Verein: " + teamGroupSummaryData.getTeam().getName() + "\n" +
	// "Icon: " + teamGroupSummaryData.getTeam().getIconURL() + "\n" +
	// "Spieleanzahl: " + teamGroupSummaryData.getGameAmount() + "\n" +
	// "Punkte: " + teamGroupSummaryData.getPoints() + "\n" +
	// "+Tore: " + teamGroupSummaryData.getGoalPlus() + "\n" +
	// "-Tore: " + teamGroupSummaryData.getGoalMinus() + "\n" +
	// "Tordifferenz: " + (teamGroupSummaryData.getGoalPlus() -
	// teamGroupSummaryData.getGoalMinus())
	//
	// );
	Collection<MatchGroupSummaryData> matchGroupList = groupListBean.getMatchGroupSummaryDataList(34);
	System.out.printf("%30s\t%20s\t%30s","Heimmannschaf", "Ergebnis", "Gastmannschaft");
	System.out.println();
	for (MatchGroupSummaryData mgsd : matchGroupList) {
	    printlnMatchForGroup(mgsd);
	}
    }

    public static void printlnMatchForGroup(MatchGroupSummaryData mgsd) {
	System.out.printf("%30s\t%20s\t%30s", mgsd.getHomeTeamName(),
		(mgsd.getHomeTeamFinalPoints() + ":" + mgsd.getGuestTeamFinalPoints()
		+ " (" + mgsd.getHomeTeamHalfPoints() + ":" + mgsd.getGuestTeamHalfPoints() + ")"), mgsd.getGuestTeamName());
	System.out.println();
	// System.out.println(
	// index + "\t" + tsgd.getTeam().getName() + "\t\t" +
	// tsgd.getGameAmount() + "\t" +
	// tsgd.getGoalPlus() + "\t" +
	// tsgd.getGoalMinus() + "\t" +
	// tsgd.getPoints()
	// );
    }

}
