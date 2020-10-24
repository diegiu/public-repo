package it.diegiu.weather.bulletin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author giudicidiego
 * 
 */
// ignore any unknown properties in JSON input without exception
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {
	
	private static final long serialVersionUID = -5847316221728466445L;
	
	private String name;
	private Coordinates coord;
	private Hourly[] hourly;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the coord
	 */
	public Coordinates getCoord() {
		return coord;
	}

	/**
	 * @param coord the coord to set
	 */
	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	/**
	 * @return the hourly
	 */
	public Hourly[] getHourly() {
		return hourly;
	}

	/**
	 * @param hourly the hourly to set
	 */
	public void setHourly(Hourly[] hourly) {
		this.hourly = hourly;
	}
}