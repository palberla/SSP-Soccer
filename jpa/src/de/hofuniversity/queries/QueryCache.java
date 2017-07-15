package de.hofuniversity.queries;

/**
 * 
 * @author Michael Jahn
 *
 */

public class QueryCache {
    
    private static QueryCache INSTANCE = null;
    
    private MatchQuery matchQuery;
    private PlayerQuery playerQuery;
    private StadiumQuery stadiumQuery;
    private TeamQuery teamQuery;
    
    private QueryCache() {}


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
    
    public static QueryCache getInstance()
    {
	if (INSTANCE == null)
	{
	    INSTANCE = new QueryCache();
	}
	
	return INSTANCE;
    }
}
