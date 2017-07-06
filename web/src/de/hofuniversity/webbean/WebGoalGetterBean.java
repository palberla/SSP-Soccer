package de.hofuniversity.webbean;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.hofuniversity.ejbbean.bean.impl.GoalGetterListBean;
import de.hofuniversity.ejbbean.data.GoalGetterSummaryData;

@ManagedBean
@ViewScoped
public class WebGoalGetterBean {
    
    private GoalGetterListBean goalGetter = new GoalGetterListBean();
    
    public List<GoalGetterSummaryData> goalGetterList() {
	return goalGetter.getGoalGetterList();
    }

}
