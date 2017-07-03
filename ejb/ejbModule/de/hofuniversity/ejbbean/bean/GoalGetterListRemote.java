package de.hofuniversity.ejbbean.bean;

import java.util.Collection;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.GoalGetterSummaryData;

@Remote
public interface GoalGetterListRemote {
    public final String MAPPED_NAME = "ejb/GoalGetterList";
    
    public Collection<GoalGetterSummaryData> getGoalGetterCollection();
}
