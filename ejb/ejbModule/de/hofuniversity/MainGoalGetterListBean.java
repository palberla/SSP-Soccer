package de.hofuniversity;

import java.util.List;

import de.hofuniversity.ejbbean.bean.impl.GoalGetterListBean;
import de.hofuniversity.ejbbean.data.GoalGetterSummaryData;

public class MainGoalGetterListBean {

    public static void main(String[] args) {
	GoalGetterListBean bean = new GoalGetterListBean();
	List<GoalGetterSummaryData> goalGetterList = bean.getGoalGetterList();
	
	System.out.printf("%35s\t%25s\t%5s\t%60s","Name", "Verein", "Tore", "Icon"); System.out.println();
	
	for (GoalGetterSummaryData ggsd : goalGetterList)
	{
	    System.out.printf("%35s\t%25s\t%5d\t%60s",ggsd.getPlayerName(), ggsd.getTeamName(), ggsd.getGoalAmount(), ggsd.getTeamIconURL()); System.out.println();
	}
	
    }

}
