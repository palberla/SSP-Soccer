package de.hofuniversity.core;

import javax.persistence.*;

import de.hofuniversity.util.ExceptionText;

/**
 * @author Michael Jahn
 *
 */

@MappedSuperclass
public abstract class Identifier
{
    	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="c_id", updatable=false, nullable=false)
	private int id = -1;
	
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		if (id < 0) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNegativeNumberIllegalArgumentExceptionMessage("id", this)); }
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Identifier)) {
			return false;
		}
		return ((Identifier)obj).getId() == this.getId() && obj.getClass() == this.getClass();
	}
}
