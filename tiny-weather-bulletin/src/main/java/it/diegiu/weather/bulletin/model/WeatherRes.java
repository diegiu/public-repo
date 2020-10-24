package it.diegiu.weather.bulletin.model;

import java.io.Serializable;

/**
 * @author giudicidiego
 * 
 */
public class WeatherRes implements Serializable {

	private static final long serialVersionUID = 6893844757379179091L;
	
	private double outside_working_hours_temp_average;
	private double outside_working_hours_feels_like_average;
	private double outside_working_hours_humidity_average;
	
	private double working_hours_temp_average;
	private double working_hours_feels_like_average;
	private double working_hours_humidity_average;
	
	public WeatherRes() {
	}
	
	public WeatherRes(double workingHoursTempAverage, double workingHoursFeelsLikeAverage, double workingHoursHumidityAverage, 
			double outsideWorkingHoursTempAverage, double outsideWorkingHoursFeelsLikeAverage, double outsideWorkingHoursHumidityAverage) {
		this.outside_working_hours_temp_average = outsideWorkingHoursTempAverage;
		this.outside_working_hours_feels_like_average = outsideWorkingHoursFeelsLikeAverage;
		this.outside_working_hours_humidity_average = outsideWorkingHoursHumidityAverage;
		this.working_hours_temp_average = workingHoursTempAverage;
		this.working_hours_feels_like_average = workingHoursFeelsLikeAverage;
		this.working_hours_humidity_average = workingHoursHumidityAverage;
	}

	/**
	 * @return the outside_working_hours_temp_average
	 */
	public double getOutside_working_hours_temp_average() {
		return outside_working_hours_temp_average;
	}

	/**
	 * @param outside_working_hours_temp_average the outside_working_hours_temp_average to set
	 */
	public void setOutside_working_hours_temp_average(double outside_working_hours_temp_average) {
		this.outside_working_hours_temp_average = outside_working_hours_temp_average;
	}

	/**
	 * @return the outside_working_hours_feels_like_average
	 */
	public double getOutside_working_hours_feels_like_average() {
		return outside_working_hours_feels_like_average;
	}

	/**
	 * @param outside_working_hours_feels_like_average the outside_working_hours_feels_like_average to set
	 */
	public void setOutside_working_hours_feels_like_average(double outside_working_hours_feels_like_average) {
		this.outside_working_hours_feels_like_average = outside_working_hours_feels_like_average;
	}

	/**
	 * @return the outside_working_hours_humidity_average
	 */
	public double getOutside_working_hours_humidity_average() {
		return outside_working_hours_humidity_average;
	}

	/**
	 * @param outside_working_hours_humidity_average the outside_working_hours_humidity_average to set
	 */
	public void setOutside_working_hours_humidity_average(double outside_working_hours_humidity_average) {
		this.outside_working_hours_humidity_average = outside_working_hours_humidity_average;
	}

	/**
	 * @return the working_hours_temp_average
	 */
	public double getWorking_hours_temp_average() {
		return working_hours_temp_average;
	}

	/**
	 * @param working_hours_temp_average the working_hours_temp_average to set
	 */
	public void setWorking_hours_temp_average(double working_hours_temp_average) {
		this.working_hours_temp_average = working_hours_temp_average;
	}

	/**
	 * @return the working_hours_feels_like_average
	 */
	public double getWorking_hours_feels_like_average() {
		return working_hours_feels_like_average;
	}

	/**
	 * @param working_hours_feels_like_average the working_hours_feels_like_average to set
	 */
	public void setWorking_hours_feels_like_average(double working_hours_feels_like_average) {
		this.working_hours_feels_like_average = working_hours_feels_like_average;
	}

	/**
	 * @return the working_hours_humidity_average
	 */
	public double getWorking_hours_humidity_average() {
		return working_hours_humidity_average;
	}

	/**
	 * @param working_hours_humidity_average the working_hours_humidity_average to set
	 */
	public void setWorking_hours_humidity_average(double working_hours_humidity_average) {
		this.working_hours_humidity_average = working_hours_humidity_average;
	}
}