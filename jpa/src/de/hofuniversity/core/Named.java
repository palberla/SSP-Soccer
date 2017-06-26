package de.hofuniversity.core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import de.hofuniversity.util.ExceptionText;

/**
 * @author Michael Jahn
 *
 */
@MappedSuperclass
public abstract class Named extends Identifier
{
	@Column(name="c_name", nullable=false)
	private String name = null;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("name", this)); }
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.getName();
	}
}
