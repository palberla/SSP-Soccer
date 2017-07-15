package de.hofuniversity.ejbbean.bean;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.MatchDetailsSummaryData;

/**
 * 
 * @author Markus Exner
 *
 */

@Remote
public interface MatchDetailsRemote {
    public final String MAPPED_NAME = "ejb/MatchDetails";
    
    public MatchDetailsSummaryData getMatchDetails(int matchId);
}
