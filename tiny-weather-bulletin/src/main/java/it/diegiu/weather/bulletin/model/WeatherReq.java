package it.diegiu.weather.bulletin.model;

import java.io.Serializable;

/**
 * @author giudicidiego
 * 
 */
public class WeatherReq implements Serializable {
	
	private static final long serialVersionUID = 7260192312341635928L;
	
	private String cityName;
	private int fromWorkingHours;
	private int toWorkingHours;
	
	public WeatherReq() {
	}
	
	public WeatherReq(String cityName) {
		this.cityName = cityName;
	}
	
	public WeatherReq(String cityName, int fromWorkingHours, int toWorkingHours) {
		this(cityName);
		this.fromWorkingHours = fromWorkingHours;
		this.toWorkingHours = toWorkingHours;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the fromWorkingHours
	 */
	public int getFromWorkingHours() {
		return fromWorkingHours;
	}

	/**
	 * @param fromWorkingHours the fromWorkingHours to set
	 */
	public void setFromWorkingHours(int fromWorkingHours) {
		this.fromWorkingHours = fromWorkingHours;
	}

	/**
	 * @return the toWorkingHours
	 */
	public int getToWorkingHours() {
		return toWorkingHours;
	}

	/**
	 * @param toWorkingHours the toWorkingHours to set
	 */
	public void setToWorkingHours(int toWorkingHours) {
		this.toWorkingHours = toWorkingHours;
	}
}