/**
 * 
 */
package de.hofuniversity.util;

import de.hofuniversity.core.Identifier;

/**
 * @author Michael Jahn
 *
 */
public class ExceptionText
{
	private static ExceptionText INSTANCE = null;
	private static StringBuffer messageBuf = null;
	
	private ExceptionText() {}
	
	private synchronized String getIllegalArgumentExceptionMessage(String variableName, String variableModi, String variableStatus, Identifier object)
	{
		if (ExceptionText.messageBuf == null)
		{
			ExceptionText.messageBuf = new StringBuffer();
		}
		else
		{
			ExceptionText.messageBuf.setLength(0);
		}
		ExceptionText.messageBuf.append("Can not ");
		ExceptionText.messageBuf.append(variableModi);
		ExceptionText.messageBuf.append(" ");
		ExceptionText.messageBuf.append(variableStatus);
		if (variableName != null)
		{
			ExceptionText.messageBuf.append(" as ");
			ExceptionText.messageBuf.append(variableName);
		}
		ExceptionText.messageBuf.append(" for ");
		if (object != null)
		{
			ExceptionText.messageBuf.append(object.getClass().getSimpleName());
			ExceptionText.messageBuf.append(" ");
			ExceptionText.messageBuf.append(object.getId());
			ExceptionText.messageBuf.append(".");
		}
		else
		{
			ExceptionText.messageBuf.append(" unknown class.");
		}
		return ExceptionText.messageBuf.toString();
	}
	
	public String getSetNullIllegalArgumentExceptionMessage(String variableName, Identifier object)
	{
		return this.getIllegalArgumentExceptionMessage(variableName, "set", "NULL", object);
	}
	
	public String getAddNullIllegalArgumentExceptionMessage(String variableName, Identifier object)
	{
		return this.getIllegalArgumentExceptionMessage(variableName, "add", "NULL", object);
	}
	
	public String getConnectNullIllegalArgumentExceptionMessage(String variableName, Identifier object)
	{
		return this.getIllegalArgumentExceptionMessage(variableName, "connect", "NULL", object);
	}
	
	public String getSetNegativeNumberIllegalArgumentExceptionMessage(String variableName, Identifier object)
	{
		return this.getIllegalArgumentExceptionMessage(variableName, "set", "negative number", object);
	}
	
	public static ExceptionText getInstance()
	{
		if (ExceptionText.INSTANCE == null)
		{
			ExceptionText.INSTANCE = new ExceptionText();
		}
		return ExceptionText.INSTANCE;
	}
}
