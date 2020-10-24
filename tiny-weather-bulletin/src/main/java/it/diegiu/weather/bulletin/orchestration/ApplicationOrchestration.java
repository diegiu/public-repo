package it.diegiu.weather.bulletin.orchestration;

import static it.diegiu.weather.bulletin.utils.ApplicationUtils.INTERNAL_SERVER_ERROR;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import it.diegiu.weather.bulletin.utils.ApplicationUtils;

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
	
	@Value("${current.weatherForecastUrl}")
	private String currentWeatherForecastUrl;
	
	@Value("${hourly.weatherForecastUrl}")
	private String hourlyWeatherForecastUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * {@inheritDoc}
	 */
	public WeatherRes getWeatherData(WeatherReq weatherReq) {
		
		Map<String, String> params = null;
		ResponseEntity<String> response = null;
		Weather weather = null;
		
		try {
			// prepare the working hours range given the input time span
			List<Integer> workingHoursIn = new LinkedList<Integer>();
			for (int i = weatherReq.getFromWorkingHours(); i <= weatherReq.getToWorkingHours(); i++) {
				workingHoursIn.add(i);
			}
			
			// prepare input data to call OpenWeather API
			params = new HashMap<String, String>(2);
		    params.put("city-name", StringUtils.trimToEmpty(weatherReq.getCityName()));
		    params.put("API-key", apiKey);
		    
			// call OpenWeather API with registered API key to retrieve only coordinates given the city name
			// see the https://openweathermap.org/current for more details
			response = restTemplate.getForEntity(currentWeatherForecastUrl, String.class, params);
			ApplicationUtils.handleResponseEntity(response, objectMapper);
			weather = objectMapper.readValue(response.getBody(), Weather.class);
			String cityName = weather.getName();
			
			// prepare input data to call OpenWeather API
			Coordinates coordinates = weather.getCoord();
			params = new HashMap<String, String>(3);
		    params.put("lat", "" + coordinates.getLat());
		    params.put("lon", "" + coordinates.getLon());
		    params.put("API-key", apiKey);
		    
		    // call OpenWeather API with registered API key to retrieve hourly forecast for 48 hours given the coordinates of the city
		 	// see the https://openweathermap.org/api/one-call-api for more details
		    response = restTemplate.getForEntity(hourlyWeatherForecastUrl, String.class, params);
		    ApplicationUtils.handleResponseEntity(response, objectMapper);
			weather = objectMapper.readValue(response.getBody(), Weather.class);
			
			List<Hourly> hourlies = Lists.newArrayList(weather.getHourly());
			List<Long> workingHoursTemp = new LinkedList<Long>();
			List<Long> workingHoursFeelsLike = new LinkedList<Long>();
			List<Long> workingHoursHumidity = new LinkedList<Long>();
			List<Long> outsideWorkingHoursTemp = new LinkedList<Long>();
			List<Long> outsideWoursFeelsLike = new LinkedList<Long>();
			List<Long> outsideWorkingHoursHumidity = new LinkedList<Long>();
			
			hourlies.forEach(hourly -> {
				// get the human-readable date from epoch time returned from OpenWeather API
				DateTime dateTime = new DateTime(hourly.getDt() * 1000, DateTimeZone.UTC);
				int hourOfDay = BigDecimal.ZERO.intValue() == dateTime.getHourOfDay() ? 24 : dateTime.getHourOfDay();
				long temp = hourly.getTemp();
				long feelsLike = hourly.getFeels_like();
				long humidity = hourly.getHumidity();
				
				if (workingHoursIn.contains(hourOfDay)) {
					workingHoursTemp.add(temp);
					workingHoursFeelsLike.add(feelsLike);
					workingHoursHumidity.add(humidity);
				} else {
					outsideWorkingHoursTemp.add(temp);
					outsideWoursFeelsLike.add(feelsLike);
					outsideWorkingHoursHumidity.add(humidity);
				}
			});
			
			// TODO remove after debugging
//			for (Hourly hourly : hourlies) {
//				// get the human-readable date from epoch time returned from OpenWeather API
//				DateTime dateTime = new DateTime(hourly.getDt() * 1000, DateTimeZone.UTC);
//				int hourOfDay = BigDecimal.ZERO.intValue() == dateTime.getHourOfDay() ? 24 : dateTime.getHourOfDay();
//				long temp = hourly.getTemp();
//				long feelsLike = hourly.getFeels_like();
//				long humidity = hourly.getHumidity();
//				
//				if (workingHoursIn.contains(hourOfDay)) {
//					workingHoursTemp.add(temp);
//					workingHoursFeelsLike.add(feelsLike);
//					workingHoursHumidity.add(humidity);
//				} else {
//					outsideWorkingHoursTemp.add(temp);
//					outsideWoursFeelsLike.add(feelsLike);
//					outsideWorkingHoursHumidity.add(humidity);
//				}
//			}
			
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
			return new WeatherRes
			(
				cityName, coordinates.getLat(), coordinates.getLon(), 
				BigDecimal.valueOf(workingHoursTempStats.getAverage()).setScale(2, RoundingMode.HALF_UP).doubleValue(), 
				BigDecimal.valueOf(workingHoursFeelsLikeStats.getAverage()).setScale(2, RoundingMode.HALF_UP).doubleValue(), 
				BigDecimal.valueOf(workingHoursHumidityStats.getAverage()).setScale(2, RoundingMode.HALF_UP).doubleValue(), 
				BigDecimal.valueOf(outsideWorkingHoursTempStats.getAverage()).setScale(2, RoundingMode.HALF_UP).doubleValue(), 
				BigDecimal.valueOf(outsideWorkingHoursFeelsLikeStats.getAverage()).setScale(2, RoundingMode.HALF_UP).doubleValue(), 
				BigDecimal.valueOf(outsideWorkingHoursHumidityStats.getAverage()).setScale(2, RoundingMode.HALF_UP).doubleValue()
			);
			
		} catch (HttpClientErrorException e) {
			int statusCode = e.getRawStatusCode();
			if (HttpStatus.NOT_FOUND.value() == statusCode) {
				throw new ApplicationException(statusCode, String.format("No results found given the city with the name %s", weatherReq.getCityName()));
			}
			throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR);
			
		} catch (Exception e1) {
			throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR);
		}
	}
}