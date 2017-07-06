package de.hofuniversity.io.xml;

import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.hofuniversity.core.cache.StadiumCache;

/**
 * 
 * @author Markus Exner
 *
 */

public class XMLStadiumsReader {

    private XMLStadiumReader stadiumReader;

    private InputStream	     inputStream;

    public XMLStadiumsReader(InputStream inputStream) {
	if (inputStream == null) {
	    throw new IllegalArgumentException("Cannot read out of a NULL InputStream.");
	}
	this.inputStream = inputStream;
    }

    public void readAllStadiums() throws JDOMException, IOException, Exception {
	this.stadiumReader = new XMLStadiumReader();
	SAXBuilder saxBuilder = new SAXBuilder();
	Document document = saxBuilder.build(inputStream);
	Element rootElement = document.getRootElement();

	for (Element stadiumElement : rootElement.getChildren("stadion")) {
	    StadiumCache.getInstance().add(this.stadiumReader.readStadium(stadiumElement));
	}
    }

}
