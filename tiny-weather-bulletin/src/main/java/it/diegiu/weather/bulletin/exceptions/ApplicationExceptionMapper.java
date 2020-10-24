package it.diegiu.weather.bulletin.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.diegiu.weather.bulletin.model.Error;

/**
 * Rest controller advice for the error handling of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
@ControllerAdvice
public class ApplicationExceptionMapper extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionMapper.class);
	
	/**
	 * The method that manage an application runtime exception.
	 * 
	 * @param e the exception raised
	 * @return ResponseEntity with the addition of HTTP status code and JSON format of the {@link Error} as body
	 * 
	 * @author giudicidiego
	 */
	@ExceptionHandler(value = { Exception.class })
	@Order(Ordered.LOWEST_PRECEDENCE)
    public ResponseEntity<Object> handleException(Exception e) {
		ResponseEntity<Object> response = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        
        if (e instanceof ApplicationException) {
        	ApplicationException applicationException = (ApplicationException) e;
        	Error error = new Error(applicationException.getStatusCode(), applicationException.getMessage());
            httpStatus = HttpStatus.valueOf(applicationException.getStatusCode());
            logger.error(e.getMessage(), e);
            response = ResponseEntity.status(httpStatus).body(error);
            
        } else {
        	Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        	logger.error(e.getMessage(), e);
        	response = ResponseEntity.status(httpStatus).body(error);
        }
        return response;
    }

}