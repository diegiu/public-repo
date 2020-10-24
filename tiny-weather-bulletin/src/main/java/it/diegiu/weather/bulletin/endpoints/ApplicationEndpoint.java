package it.diegiu.weather.bulletin.endpoints;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.diegiu.weather.bulletin.exceptions.ApplicationException;
import it.diegiu.weather.bulletin.model.WeatherReq;
import it.diegiu.weather.bulletin.model.WeatherRes;
import it.diegiu.weather.bulletin.orchestration.ApplicationOrchestration;

/**
 * Rest controller of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
@RequestMapping("/")
@RestController
@Api(value = "tiny-weather-bulletin", tags = { "tiny-weather-bulletin" })
public class ApplicationEndpoint {
	
	@Autowired
	ApplicationOrchestration applicationOrchestration;
	
	/**
	 * The method that retrieve the average maximum/feels-like temperatures and humidity during/outside the 
	 * working hours for the next 48 hours through the name of the city and a time span.
	 * 
	 * @param cityName the name of the city that the forecast is needed
	 * @param fromHours from hours that the forecast is needed
	 * @param toHours to hours that the forecast is needed
	 * @return ResponseEntity with the addition of HTTP status code and JSON format of the {@link WeatherRes} as body
	 * @throws ApplicationException
	 * 
	 * @author giudicidiego
	 */
	@RequestMapping(value = "/weather/bulletin/averages",
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Retrieve the average maximum/feels-like temperatures and humidity during/outside the working hours for the next 48 hours", tags = {"tiny-weather-bulletin"})
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<WeatherRes> getWeatherData (
		@ApiParam(value = "The literal name of the city that the forecast is needed", required = true) @NotEmpty @RequestParam final String cityName, 
		@ApiParam(value = "From working hours that the forecast is needed", required = true, defaultValue = "9") @NotNull @RequestParam final Integer fromHours, 
		@ApiParam(value = "To working hours that the forecast is needed", required = true, defaultValue = "18") @NotNull @RequestParam final Integer toHours) throws ApplicationException {
		
		// call orchestration service and built response body
		WeatherRes weatherRes = applicationOrchestration.getWeatherData(new WeatherReq(cityName, fromHours, toHours));
		return ResponseEntity.ok().body(weatherRes);
	}

}
