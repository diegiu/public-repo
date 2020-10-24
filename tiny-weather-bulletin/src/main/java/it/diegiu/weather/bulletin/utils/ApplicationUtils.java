package it.diegiu.weather.bulletin.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.diegiu.weather.bulletin.exceptions.ApplicationException;
import it.diegiu.weather.bulletin.model.Error;

/**
 * Class containing the utility methods of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
public class ApplicationUtils {

	private ApplicationUtils() {
	}
	
	/**
	 * The method that handle a response entity also containing the HTTP status code and JSON body.
	 * 
	 * @param response; as {@link ResponseEntity}
	 * @param objectMapper 
	 * 		an instance of the mapper used for the serialization and/or deserialization operation; as {@link ObjectMapper}
	 * @throws ApplicationException
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
			throw new ApplicationException(statusCode, HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}
}