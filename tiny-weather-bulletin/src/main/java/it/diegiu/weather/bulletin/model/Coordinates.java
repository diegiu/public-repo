package it.diegiu.weather.bulletin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author giudicidiego
 * 
 */
// ignore any unknown properties in JSON input without exception
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates implements Serializable {
	
	private static final long serialVersionUID = 2786211063148712129L;
	
	private long lat;
	private long lon;
	
	/**
	 * @return the lat
	 */
	public long getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(long lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public long getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(long lon) {
		this.lon = lon;
	}
}