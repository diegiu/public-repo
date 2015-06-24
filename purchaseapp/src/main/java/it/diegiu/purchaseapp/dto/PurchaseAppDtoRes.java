/**
 * 
 */
package it.diegiu.purchaseapp.dto;

import it.diegiu.purchaseapp.data.Basket;

import java.io.Serializable;

/**
 * @author Diego Giudici
 *
 */
public class PurchaseAppDtoRes implements Serializable {
	
	private static final long serialVersionUID = 5904889611340820177L;
	
	private Basket basket;
	
	public PurchaseAppDtoRes() {
		
	}
	
	public PurchaseAppDtoRes(Basket basket) {
		this.basket = basket;
	}

	/**
	 * @return the basket
	 */
	public Basket getBasket() {
		return basket;
	}

	/**
	 * @param basket the basket to set
	 */
	public void setBasket(Basket basket) {
		this.basket = basket;
	}

}
