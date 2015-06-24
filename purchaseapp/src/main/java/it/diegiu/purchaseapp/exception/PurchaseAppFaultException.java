/**
 * 
 */
package it.diegiu.purchaseapp.exception;

/**
 * @author Diego Giudici
 *
 */
public class PurchaseAppFaultException extends Exception {
	
	private static final long serialVersionUID = 2725904612419775153L;

	public PurchaseAppFaultException() {
		
	}

	public PurchaseAppFaultException(String message) {
		super(message);
	}

	public PurchaseAppFaultException(Throwable cause) {
		super(cause);
	}

	public PurchaseAppFaultException(String message, Throwable cause) {
		super(message, cause);
	}

	public PurchaseAppFaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}