package it.diegiu.weather.bulletin.exceptions;

public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 949433860003247071L;
	
	private int statusCode;
	
	public ApplicationException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}
	
	public ApplicationException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
}