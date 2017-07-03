package de.hofuniversity.bean;

import java.util.Collection;

import javax.ejb.Stateless;

import de.hofuniversity.bean.goalgetterlist.DefaultGoalGetterSummaryData;
import de.hofuniversity.bean.goalgetterlist.GoalGetterSummaryData;

@Stateless
public class GoalGetterListBean {
    
    public Collection<GoalGetterSummaryData> getGoalGetterCollection() {
	DefaultGoalGetterSummaryData dggsd = new DefaultGoalGetterSummaryData();
	return null;
	
    }

}
