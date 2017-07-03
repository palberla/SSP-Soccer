package de.hofuniversity.ejbbean.bean;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.MatchDetailSummaryData;

@Remote
public interface MatchDetailsRemote {
    public final String MAPPED_NAME = "ejb/MatchDetails";
    
    public MatchDetailSummaryData getMatchDetail();
}
