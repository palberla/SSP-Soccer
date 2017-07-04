package de.hofuniversity.queries;

public class QueryCache {
    
    private static QueryCache INSTANCE = null;
    
    private GoalQuery goalQuery;
    private MatchQuery matchQuery;
    private PlayerQuery playerQuery;
    private StadiumQuery stadiumQuery;
    private TeamQuery teamQuery;
    
    private QueryCache() {}
    
    public GoalQuery getGoalQuery() {
	if (goalQuery == null)
	{
	    this.goalQuery = new GoalQuery();
	}
        return goalQuery;
    }

    public MatchQuery getMatchQuery() {
	if (matchQuery == null)
	{
	    this.matchQuery = new MatchQuery();
	}
        return matchQuery;
    }

    public PlayerQuery getPlayerQuery() {
	if (playerQuery == null)
	{
	    this.playerQuery = new PlayerQuery();
	}
        return playerQuery;
    }

    public StadiumQuery getStadiumQuery() {
	if (stadiumQuery == null)
	{
	    this.stadiumQuery = new StadiumQuery();
	}
        return stadiumQuery;
    }

    public TeamQuery getTeamQuery() {
	if (teamQuery == null)
	{
	    this.teamQuery = new TeamQuery();
	}
        return teamQuery;
    }
    
    public static QueryCache getQueryCache()
    {
	if (INSTANCE == null)
	{
	    INSTANCE = new QueryCache();
	}
	
	return INSTANCE;
    }
}
