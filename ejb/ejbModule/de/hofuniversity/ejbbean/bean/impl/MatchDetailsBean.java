package de.hofuniversity.ejbbean.bean.impl;

import javax.ejb.Stateless;

import de.hofuniversity.ejbbean.bean.MatchDetailsRemote;
import de.hofuniversity.ejbbean.data.MatchDetailSummaryData;

@Stateless(name = MatchDetailsRemote.MAPPED_NAME, mappedName = MatchDetailsRemote.MAPPED_NAME)
public class MatchDetailsBean implements MatchDetailsRemote {
    public MatchDetailSummaryData getMatchDetail() {
	return null;
    }
}
