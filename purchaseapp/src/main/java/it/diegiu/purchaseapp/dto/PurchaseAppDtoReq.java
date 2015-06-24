/**
 * 
 */
package it.diegiu.purchaseapp.dto;

import it.diegiu.purchaseapp.data.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Giudici
 *
 */
public class PurchaseAppDtoReq implements Serializable {
	
	private static final long serialVersionUID = -2388289746973902934L;
	
	List<Item> itemList = new ArrayList<Item>();
	
	public PurchaseAppDtoReq() {
		
	}
	
	public PurchaseAppDtoReq(List<Item> itemList) {
		this.itemList = itemList;
	}

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

}
