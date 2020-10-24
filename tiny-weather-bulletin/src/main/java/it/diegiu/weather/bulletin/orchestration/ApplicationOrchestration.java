package it.diegiu.weather.bulletin.orchestration;

import static it.diegiu.weather.bulletin.utils.ApplicationUtils.handleResponseEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import it.diegiu.weather.bulletin.exceptions.ApplicationException;
import it.diegiu.weather.bulletin.model.Coordinates;
import it.diegiu.weather.bulletin.model.Hourly;
import it.diegiu.weather.bulletin.model.Weather;
import it.diegiu.weather.bulletin.model.WeatherReq;
import it.diegiu.weather.bulletin.model.WeatherRes;

/**
 * The implementation of the orchestration service of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
@Service
public class ApplicationOrchestration implements IApplicationOrchestration {
	
	// unique API key to invoke OpenWeather API
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * {@inheritDoc}
	 */
	public WeatherRes getWeatherData(WeatherReq weatherReq) {
		
		String resourceUrl = null;
		Map<String, String> params = null;
		ResponseEntity<String> response = null;
		Weather weather = null;
		
		try {
			List<Integer> workingHoursIn = new LinkedList<Integer>();
			for (int i = weatherReq.getFromWorkingHours(); i < weatherReq.getToWorkingHours(); i++) {
				workingHoursIn.add(i);
			}
			
			// prepare input data to call OpenWeather API
			resourceUrl = "http://api.openweathermap.org/data/2.5/weather?q={city-name}&units=metric&appid={API-key}";
			params = new HashMap<String, String>(2);
		    params.put("city-name", StringUtils.trimToEmpty(weatherReq.getCityName()));
		    params.put("API-key", apiKey);
		    
			// call OpenWeather API with registered API key to retrieve only coordinates given the city name
			// see the https://openweathermap.org/current for more details
			response = restTemplate.getForEntity(resourceUrl, String.class, params);
			handleResponseEntity(response, objectMapper);
			weather = objectMapper.readValue(response.getBody(), Weather.class);
			
			// prepare input data to call OpenWeather API
			resourceUrl = "http://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude=current,minutely,daily,alerts&units=metric&appid={API-key}";
			Coordinates coordinates = weather.getCoord();
			params = new HashMap<String, String>(3);
		    params.put("lat", "" + coordinates.getLat());
		    params.put("lon", "" + coordinates.getLon());
		    params.put("API-key", apiKey);
		    
		    // call OpenWeather API with registered API key to retrieve hourly forecast for 48 hours given the coordinates of the city
		 	// see the https://openweathermap.org/api/one-call-api for more details
		    response = restTemplate.getForEntity(resourceUrl, String.class, params);
			handleResponseEntity(response, objectMapper);
			weather = objectMapper.readValue(response.getBody(), Weather.class);
			
			List<Hourly> hourlies = Lists.newArrayList(weather.getHourly());
			List<Long> workingHoursTemp = new LinkedList<Long>();
			List<Long> workingHoursFeelsLike = new LinkedList<Long>();
			List<Long> workingHoursHumidity = new LinkedList<Long>();
			List<Long> outsideWorkingHoursTemp = new LinkedList<Long>();
			List<Long> outsideWoursFeelsLike = new LinkedList<Long>();
			List<Long> outsideWorkingHoursHumidity = new LinkedList<Long>();
			
			for (Hourly hourly : hourlies) {
				// get the human-readable date from epoch time returned from OpenWeather API
				DateTime dateTime = new DateTime(hourly.getDt() * 1000, DateTimeZone.UTC);
				long temp = hourly.getTemp();
				long feelsLike = hourly.getFeels_like();
				long humidity = hourly.getHumidity();
				
				for (int wHourIn : workingHoursIn) {
					if (wHourIn == dateTime.getHourOfDay()) {
						workingHoursTemp.add(temp);
						workingHoursFeelsLike.add(feelsLike);
						workingHoursHumidity.add(humidity);
					} else {
						outsideWorkingHoursTemp.add(temp);
						outsideWoursFeelsLike.add(feelsLike);
						outsideWorkingHoursHumidity.add(humidity);
					}
				}
			}
			
//			hourlies.forEach(hourly -> {
			// get the human-readable date from epoch time returned from OpenWeather API
//				DateTime dateTime = new DateTime(hourly.getDt() * 1000, DateTimeZone.UTC);
//				long temp = hourly.getTemp();
//				long feelsLike = hourly.getFeels_like();
//				long humidity = hourly.getHumidity();
//				
//				workingHoursIn.forEach(wHourIn -> {
//					if (wHourIn == dateTime.getHourOfDay()) {
//						workingHoursTemp.add(temp);
//						workingHoursFeelsLike.add(feelsLike);
//						workingHoursHumidity.add(humidity);
//					} else {
//						outsideWorkingHoursTemp.add(temp);
//						outsideWoursFeelsLike.add(feelsLike);
//						outsideWorkingHoursHumidity.add(humidity);
//					}
//				});
//			});
			
			// Find maximum, minimum, sum and average of a list(s)
			LongSummaryStatistics workingHoursTempStats = 
				workingHoursTemp.stream().mapToLong((x) -> x).summaryStatistics();
			LongSummaryStatistics workingHoursFeelsLikeStats = 
					workingHoursFeelsLike.stream().mapToLong((x) -> x).summaryStatistics();
			LongSummaryStatistics workingHoursHumidityStats = 
				workingHoursHumidity.stream().mapToLong((x) -> x).summaryStatistics();
			
			LongSummaryStatistics outsideWorkingHoursTempStats = 
				outsideWorkingHoursTemp.stream().mapToLong((x) -> x).summaryStatistics();
			LongSummaryStatistics outsideWorkingHoursFeelsLikeStats = 
					outsideWoursFeelsLike.stream().mapToLong((x) -> x).summaryStatistics();
			LongSummaryStatistics outsideWorkingHoursHumidityStats = 
				outsideWorkingHoursHumidity.stream().mapToLong((x) -> x).summaryStatistics();
			
			// return response ...
			// average average maximum/feels-like temperatures and humidity during/outside the working hours
			return new WeatherRes(workingHoursTempStats.getAverage(), workingHoursFeelsLikeStats.getAverage(), workingHoursHumidityStats.getAverage(), 
				outsideWorkingHoursTempStats.getAverage(), outsideWorkingHoursFeelsLikeStats.getAverage(), outsideWorkingHoursHumidityStats.getAverage());
			
		} catch (HttpClientErrorException e) {
			int statusCode = e.getRawStatusCode();
			if (HttpStatus.NOT_FOUND.value() == statusCode) {
				throw new ApplicationException(statusCode, String.format("No results found given the city with the name %s", weatherReq.getCityName()));
			}
			throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
			
		} catch (Exception e1) {
			throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}
}