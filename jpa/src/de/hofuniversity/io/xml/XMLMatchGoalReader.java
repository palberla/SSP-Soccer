package de.hofuniversity.io.xml;

import org.jdom2.Element;

import de.hofuniversity.core.Goal;
import de.hofuniversity.core.cache.GoalCache;

/**
 * 
 * @author Michael Jahn
 *
 */

public class XMLMatchGoalReader {
    
    private XMLMatchGoalPlayerReader xmlMatchGoalPlayerReader = null;

    public Goal readGoal(Element goalElement) {
	
	if (goalElement == null) {
	    throw new IllegalArgumentException("Cannot read NULL element for goals.");
	}
	
	if (xmlMatchGoalPlayerReader == null) {
	    xmlMatchGoalPlayerReader = new XMLMatchGoalPlayerReader();
	}

	Goal goal = new Goal();

	goal.setPointsHome(Integer.parseInt(goalElement.getChildText("goalScoreTeam1")));
	goal.setPointsGuest(Integer.parseInt(goalElement.getChildText("goalScoreTeam2")));
	goal.setMinute(Integer.parseInt(goalElement.getChildText("goalMatchMinute")));

	goal.setOwnGoal(Boolean.parseBoolean(goalElement.getChildText("goalOwnGoal")));
	goal.setOvertimeGoal(Boolean.parseBoolean(goalElement.getChildText("goalOvertime")));
	goal.setPenaltyGoal(Boolean.parseBoolean(goalElement.getChildText("goalPenalty")));
	
	goal.conntectToPlayer(this.xmlMatchGoalPlayerReader.readPlayer(goalElement));
	
	Element goalCommentElement = goalElement.getChild("goalComment");
	
	if (goalCommentElement != null && goalCommentElement.getText().length() > 0)
	{
	    goal.setComment(goalCommentElement.getText());
	}
	
	GoalCache.getInstance().add(goal);
	
	return goal;

    }
}
