package it.diegiu.weather.bulletin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author giudicidiego
 * 
 */
// ignore any unknown properties in JSON input without exception
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hourly implements Serializable {
	
	private static final long serialVersionUID = -5557640348223105293L;
	
	private long dt;
	private long temp;
	private long feels_like;
	private long pressure;
	private long humidity;
	
	/**
	 * @return the dt
	 */
	public long getDt() {
		return dt;
	}
	/**
	 * @param dt the dt to set
	 */
	public void setDt(long dt) {
		this.dt = dt;
	}
	/**
	 * @return the temp
	 */
	public long getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(long temp) {
		this.temp = temp;
	}
	/**
	 * @return the feels_like
	 */
	public long getFeels_like() {
		return feels_like;
	}
	/**
	 * @param feels_like the feels_like to set
	 */
	public void setFeels_like(long feels_like) {
		this.feels_like = feels_like;
	}
	/**
	 * @return the pressure
	 */
	public long getPressure() {
		return pressure;
	}
	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(long pressure) {
		this.pressure = pressure;
	}
	/**
	 * @return the humidity
	 */
	public long getHumidity() {
		return humidity;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(long humidity) {
		this.humidity = humidity;
	}
}