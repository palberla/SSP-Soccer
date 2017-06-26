/**
 * 
 */
package de.hofuniversity.io.xml.util;

/**
 * @author Michael Jahn
 *
 */
public class PlayerNameNormalizer
{
	/**
	 * 
	 */
	public PlayerNameNormalizer()
	{
	}
	
	public String getNormalizedPlayerName(String toNormalizeName)
	{
	    if (toNormalizeName == null)
		{
			throw new IllegalArgumentException("Cannot normalize a NULL string.");
		}
	    toNormalizeName = toNormalizeName.trim();
		if (toNormalizeName.isEmpty())
		{
			throw new IllegalArgumentException("Cannot normalize an empty string.");
		}
		
		if (toNormalizeName.indexOf(',') > 1)
		{
			String[] NameStringArray = toNormalizeName.split(",");
			toNormalizeName = NameStringArray[1].trim() + " " + NameStringArray[0].trim(); 
		}
		return toNormalizeName;
	}
}
