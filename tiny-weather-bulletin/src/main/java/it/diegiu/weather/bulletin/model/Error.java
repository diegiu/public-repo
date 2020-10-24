package it.diegiu.weather.bulletin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author giudicidiego
 * 
 */
//ignore any unknown properties in JSON input without exception
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error implements Serializable {
	
	private static final long serialVersionUID = 550049023807245138L;
	
	private String message;
	private int cod;
	
	public Error() {
	}
	
	public Error(int cod, String message) {
		this.cod = cod;
		this.message = message;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the cod
	 */
	public int getCod() {
		return cod;
	}

	/**
	 * @param cod the cod to set
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}
}