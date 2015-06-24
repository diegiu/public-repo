/**
 * 
 */
package it.diegiu.purchaseapp.consumer;

import it.diegiu.purchaseapp.dto.PurchaseAppDtoReq;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoRes;
import it.diegiu.purchaseapp.exception.PurchaseAppFaultException;
import it.diegiu.purchaseapp.service.PurchaseAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Diego Giudici
 *
 */
@Component
public class PurchaseAppConsumer {
	
	private PurchaseAppService service;

	/**
	 * @param service the service to set
	 */
	@Autowired
	public void setService(PurchaseAppService service) {
		this.service = service;
	}
	
	public PurchaseAppDtoRes processAddItems(PurchaseAppDtoReq dtoReq) throws PurchaseAppFaultException {
		return service.addItems(dtoReq);
	}

}
