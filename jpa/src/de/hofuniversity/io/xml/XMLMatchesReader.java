package de.hofuniversity.io.xml;

import java.io.IOException;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.hofuniversity.core.cache.MatchCache;

/**
 * @author Markus Exner
 *
 */

public class XMLMatchesReader {

    private XMLMatchReader matchReader;

    private InputStream	   inputStream;

    public XMLMatchesReader(InputStream inputStream) {
	if (inputStream == null) {
	    throw new IllegalArgumentException("Cannot read out of a NULL InputStream.");
	}
	this.inputStream = inputStream;
    }

    public void readAllMatches() throws JDOMException, IOException, Exception {
	this.matchReader = new XMLMatchReader();
	SAXBuilder saxBuilder = new SAXBuilder();
	Document document = saxBuilder.build(inputStream);
	Element rootElement = document.getRootElement();
	for (Element matchData : rootElement.getChildren("Matchdata")) {
	    MatchCache.getInstance().add(this.matchReader.readMatch(matchData));
	}
    }

}
