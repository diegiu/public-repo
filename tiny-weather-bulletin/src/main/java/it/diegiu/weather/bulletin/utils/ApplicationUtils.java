package it.diegiu.weather.bulletin.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.diegiu.weather.bulletin.exceptions.ApplicationException;
import it.diegiu.weather.bulletin.model.Error;
import it.diegiu.weather.bulletin.model.WeatherReq;

/**
 * Class containing the constants and the utility methods of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
public class ApplicationUtils {

	private ApplicationUtils() {
	}
	
	public static final String SUCCESSFUL_OPERATION = "Successful operation";
	public static final String INVALID_PARAMS = "Invalid parameters";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	
	/**
	 * The method which validates the input parameters after rest controller validation annotations.
	 * 
	 * @param weatherReq the input data provided; as {@link WeatherReq}
	 * @author giudicidiego
	 */
	public static void validateReq(WeatherReq weatherReq) {
		int fromHours = weatherReq.getFromWorkingHours();
		int toHours = weatherReq.getToWorkingHours();
		if (toHours <= fromHours) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST.value(), 
				String.format("To working hours [%s] passed in input must be greater than From working hours [%s]", toHours, fromHours));
		}
	}
	
	/**
	 * The method that handle a response entity also containing the HTTP status code and JSON body.
	 * 
	 * @param response; as {@link ResponseEntity}
	 * @param objectMapper 
	 * 		an instance of the mapper used for the serialization and/or deserialization operation; as {@link ObjectMapper}
	 * @throws Exception
	 * 
	 * @author giudicidiego
	 */
	public static void handleResponseEntity(ResponseEntity<String> response, ObjectMapper objectMapper) throws Exception {
		int statusCode = response.getStatusCodeValue();
		String jsonRes = response.getBody();
		
		if (HttpStatus.OK.value() != statusCode) {
			if (StringUtils.isNotEmpty(jsonRes)) {
				Error error = objectMapper.readValue(jsonRes, Error.class);
				throw new ApplicationException(error.getCod(), error.getMessage());
			}
			throw new ApplicationException(statusCode, INTERNAL_SERVER_ERROR);
		}
	}
}