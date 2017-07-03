package de.hofuniversity;

import de.hofuniversity.ejbbean.bean.impl.MatchDetailsBean;
import de.hofuniversity.ejbbean.data.MatchDetailsGoalSummaryData;
import de.hofuniversity.ejbbean.data.MatchDetailsSummaryData;

public class MainMatchDetailsBean {

    public static void main(String[] args) {
	MatchDetailsBean mdb = new MatchDetailsBean();
	
	MatchDetailsSummaryData mdsd = mdb.getMatchDetails(1);
	
	System.out.printf("%25s\t%1d:%1d (%1d:%1d)\t%25s", mdsd.getHomeTeamName(), mdsd.getFinalHomeScore(), mdsd.getFinalGuestScore(), mdsd.getHalfHomeScore(), mdsd.getHalfGuestScore(), mdsd.getGuestTeamName());
	System.out.println();
	
	System.out.printf("%3s\t%35s\t%35s", "Tor", "Torschütze", "Kommentar");
	    System.out.println();
	for (MatchDetailsGoalSummaryData mdgsd : mdsd.getGoalList())
	{
	    System.out.printf("%1d:%1d\t%35s\t%35s", mdgsd.getHomeScore(), mdgsd.getGuestScore(), mdgsd.getGoalGetterName(), mdgsd.getComment());
	    System.out.println();
	}

    }

}
