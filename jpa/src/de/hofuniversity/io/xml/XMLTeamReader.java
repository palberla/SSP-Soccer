/**
 * 
 */
package de.hofuniversity.io.xml;

import org.jdom2.Element;

import de.hofuniversity.core.Team;

/**
 * @author Michael Jahn
 *
 */
public class XMLTeamReader
{
	private XMLTeamPlayerReader xmlPlayerReader;
	
	public XMLTeamReader() {}
	
	public Team readTeam(Element teamElement)
	{
		if (teamElement == null) { throw new IllegalArgumentException("Cannot read NULL element for team."); }
		
		if (this.xmlPlayerReader == null) { this.xmlPlayerReader = new XMLTeamPlayerReader(); }
		
		Team team = new Team();
		
		team.setId(Integer.parseInt(teamElement.getChildText("teamID")));
		team.setName(teamElement.getChildText("teamName"));
		team.setIconURL(teamElement.getChildText("teamIconURL"));
		for (Element playerElement : teamElement.getChildren("player"))
		{
		    team.conntectToPlayer(this.xmlPlayerReader.readPlayer(playerElement));
		}
		return team;
	}
}
