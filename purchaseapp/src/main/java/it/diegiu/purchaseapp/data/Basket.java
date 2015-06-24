/**
 * 
 */
package it.diegiu.purchaseapp.data;

import java.util.List;

/**
 * @author Diego Giudici
 *
 */
public class Basket {
	
	private List<Item> itemList;
	private double salesTaxes;
	private double prices;

	/**
	 * @return the itemList
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the salesTaxes
	 */
	public double getSalesTaxes() {
		return salesTaxes;
	}

	/**
	 * @param salesTaxes the salesTaxes to set
	 */
	public void setSalesTaxes(double salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	/**
	 * @return the prices
	 */
	public double getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(double prices) {
		this.prices = prices;
	}

}
