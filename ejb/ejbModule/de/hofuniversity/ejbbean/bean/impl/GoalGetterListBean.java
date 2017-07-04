package de.hofuniversity.ejbbean.bean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.hofuniversity.core.Player;
import de.hofuniversity.ejbbean.bean.GoalGetterListRemote;
import de.hofuniversity.ejbbean.data.GoalGetterSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultGoalGetterSummaryData;
import de.hofuniversity.queries.PlayerQuery;
import de.hofuniversity.queries.QueryCache;

@Stateless(name = GoalGetterListRemote.MAPPED_NAME, mappedName = GoalGetterListRemote.MAPPED_NAME)
public class GoalGetterListBean implements GoalGetterListRemote {
    
    private QueryCache queryCache;
    
    public GoalGetterListBean() {}
    
    private QueryCache getQueryCache()
    {
	if (this.queryCache == null)
	{
	    this.queryCache = QueryCache.getQueryCache();
	}
	return this.queryCache;
    }
    
    private PlayerQuery getPlayerQuery()
    {
	return this.getQueryCache().getPlayerQuery();
    }
    
    @Override
    public List<GoalGetterSummaryData> getGoalGetterList() {
	List<GoalGetterSummaryData> goalGetterList = new ArrayList<GoalGetterSummaryData>();
	List<Object[]> goalGetterData = this.getPlayerQuery().getGoalGetterDataList();
	
	for (Object[] objectArray : goalGetterData)
	{
	    DefaultGoalGetterSummaryData dggsd = new DefaultGoalGetterSummaryData();
	    Player player = (Player) objectArray[0];
	    int goalAmount = (int)((long)objectArray[1]);
	    dggsd.setGoalAmount(goalAmount);
	    dggsd.setPlayerName(player.getName());
	    dggsd.setTeamIconURL(player.getTeam().getIconURL());
	    dggsd.setTeamName(player.getTeam().getName());
	    goalGetterList.add(dggsd);
	}
	return goalGetterList;
    }
}
