/**
 * 
 */
package it.diegiu.purchaseapp.service;

import it.diegiu.purchaseapp.dto.PurchaseAppDtoReq;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoRes;
import it.diegiu.purchaseapp.exception.PurchaseAppFaultException;

/**
 * @author Diego Giudici
 *
 */
public interface PurchaseAppService {

	PurchaseAppDtoRes addItems(PurchaseAppDtoReq dtoReq) throws PurchaseAppFaultException;
	
}
