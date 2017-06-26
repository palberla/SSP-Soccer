/**
 * 
 */
package de.hofuniversity.io.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.hofuniversity.core.cache.TeamCache;

/**
 * @author Michael Jahn
 *
 */
public class XMLTeamsReader
{
	private XMLTeamReader teamReader;
	private InputStream inputStream;
	
	public XMLTeamsReader(InputStream inputStream)
	{
		if (inputStream == null)
		{
			throw new IllegalArgumentException("Cannot read out of a NULL InputStream.");
		}
		this.inputStream = inputStream;
	}
	
	public void readAllTeams() throws JDOMException, IOException
	{
		this.teamReader = new XMLTeamReader();
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputStream);
		Element rootElement = document.getRootElement();
		for (Element teamElement : rootElement.getChildren("Team"))
		{
			TeamCache.getInstance().add(this.teamReader.readTeam(teamElement));
		}
	}
}
