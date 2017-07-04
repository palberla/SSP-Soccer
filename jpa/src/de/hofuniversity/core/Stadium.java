package de.hofuniversity.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 */

/**
 * @author Michael Jahn, Markus Exner
 *
 */
@Entity
@Table(name="t_stadium")
public class Stadium extends Named implements Serializable
{	
    	@Column(name="c_capacity", nullable=false)
    	private int viewers;
    	@Column(name="c_city", nullable=false)
    	private String city;
    	@Column(name="c_address", nullable=false)
    	private String address;
    	@Column(name="c_picture_outside_url", nullable=false)
    	private String imageOutside;
    	@Column(name="c_picture_inside_url", nullable=false)
    	private String imageInside;
    	@Embedded
    	private GeologicalCoordinates geologicalCoordinates;

	public Stadium() {}

	public int getViewers() {
	    return viewers;
	}

	public void setViewers(int viewers) {
	    this.viewers = viewers;
	}

	public String getCity() {
	    return city;
	}

	public void setCity(String city) {
	    this.city = city;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	}

	public String getImageOutside() {
	    return imageOutside;
	}

	public void setImageOutside(String imageOutside) {
	    this.imageOutside = imageOutside;
	}

	public String getImageInside() {
	    return imageInside;
	}

	public void setImageInside(String imageInside) {
	    this.imageInside = imageInside;
	}
	
	public GeologicalCoordinates getGeologicalCoordinates() {
	    return geologicalCoordinates;
	}

	public void setGeologicalCoordinates(GeologicalCoordinates geologicalCoordinates) {
	    this.geologicalCoordinates = geologicalCoordinates;
	}
	
	@Override
	public String toString() {
	return this.getName() + "[" + this.getId() + "], " + this.getAddress() + " in" + this.getCity() + ", hat " + this.getViewers() + " Plätze " + "\n"
		+ "URL-Außen: " + this.getImageOutside() + "\n"
		+ "URL-Innen: " + this.getImageInside() + "\n"
		+ "Koordinaten: " + this.getGeologicalCoordinates();
	}
	
//	@Override
//	public String toString() {
//	return this.getName() + ", " + this.getAddress();
//	}
}
