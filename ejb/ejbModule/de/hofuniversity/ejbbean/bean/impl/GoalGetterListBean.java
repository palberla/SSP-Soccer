package de.hofuniversity.ejbbean.bean.impl;

import java.util.Collection;

import javax.ejb.Stateless;

import de.hofuniversity.ejbbean.bean.GoalGetterListRemote;
import de.hofuniversity.ejbbean.data.GoalGetterSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultGoalGetterSummaryData;

@Stateless(name = GoalGetterListRemote.MAPPED_NAME, mappedName = GoalGetterListRemote.MAPPED_NAME)
public class GoalGetterListBean implements GoalGetterListRemote {
    
    public Collection<GoalGetterSummaryData> getGoalGetterCollection() {
	DefaultGoalGetterSummaryData dggsd = new DefaultGoalGetterSummaryData();
	return null;
    }
}
