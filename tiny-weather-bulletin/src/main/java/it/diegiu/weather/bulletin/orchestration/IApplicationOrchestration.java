package it.diegiu.weather.bulletin.orchestration;

import it.diegiu.weather.bulletin.exceptions.ApplicationException;
import it.diegiu.weather.bulletin.model.WeatherReq;
import it.diegiu.weather.bulletin.model.WeatherRes;

/**
 * The abstraction of the orchestration service of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
public interface IApplicationOrchestration {
	
	/**
	 * The method that retrieve the average maximum/feels-like temperatures and humidity during/outside the  
	 * working hours for the next 48 hours through the name of the city and a time span; using OpenWeather API.
	 * 
	 * @param weatherReq the input data provided; as {@link WeatherReq}
	 * @return the weather data; as {@link WeatherRes}
	 * @throws ApplicationException
	 * 
	 * @author giudicidiego
	 */
	public WeatherRes getWeatherData(WeatherReq weatherReq) throws ApplicationException;
}