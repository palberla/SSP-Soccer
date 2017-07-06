package de.hofuniversity.io.xml;


import org.jdom2.Element;

import de.hofuniversity.core.GeologicalCoordinates;
import de.hofuniversity.core.Stadium;
import de.hofuniversity.core.cache.TeamCache;

/**
 * 
 * @author Markus Exner
 *
 */

public class XMLStadiumReader {
    
    
    public XMLStadiumReader() {}
    
    public Stadium readStadium(Element stadiumElement) {
	
	if (stadiumElement == null) {
	    throw new IllegalArgumentException("Can not read NULL element for stadium.");
	}
	
	Stadium stadium = new Stadium();
	
	stadium.setId(Integer.parseInt(stadiumElement.getAttributeValue("id")));
	
	stadium.setName(stadiumElement.getChildText("name"));
	
	Element locationElement = stadiumElement.getChild("location");
	stadium.setCity(locationElement.getChildText("city"));
	stadium.setAddress(locationElement.getChildText("adress"));
	
	stadium.setViewers(Integer.parseInt(stadiumElement.getChildText("places")));
	
	Element imagesElement = stadiumElement.getChild("images");
	stadium.setImageOutside(imagesElement.getChild("outside").getAttributeValue("url"));
	stadium.setImageInside(imagesElement.getChild("inside").getAttributeValue("url"));
	
	GeologicalCoordinates geologicalCoordinates = new GeologicalCoordinates();
	
	geologicalCoordinates.setLongitude(Double.parseDouble(stadiumElement.getChildText("longitude")));
	geologicalCoordinates.setLatitude(Double.parseDouble(stadiumElement.getChildText("latitude")));
	
	stadium.setGeologicalCoordinates(geologicalCoordinates);
		
	int teamId = Integer.parseInt(stadiumElement.getAttributeValue("team"));
	
	if (TeamCache.getInstance().contains(teamId))
	{
	    TeamCache.getInstance().get(teamId).setStadium(stadium);
	}
	
	return stadium;
    }

}
