package de.hofuniversity.io.xml;

import org.jdom2.Element;

import de.hofuniversity.core.Result;
import de.hofuniversity.core.cache.ResultCache;

/**
 * @author Markus Exner
 *
 */

public class XMLMatchResultReader {
    
    public XMLMatchResultReader() {}
    
    public Result readMatchResult(Element resultElement) {
	
	Result result = new Result();
	
	result.setId(Integer.parseInt(resultElement.getChildText("resultTypeId")));	
	result.setPointsHome(Integer.parseInt(resultElement.getChildText("pointsTeam1")));
	result.setPointsGuest(Integer.parseInt(resultElement.getChildText("pointsTeam2")));
	
	ResultCache.getInstance().add(result);
	
	return result;
	
    }

}
