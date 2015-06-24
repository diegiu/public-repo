/**
 * 
 */
package it.diegiu.purchaseapp.util;

/**
 * @author Diego Giudici
 *
 */
public enum ItemCategory {
	
	BOOKS(1), FOOD(2), MEDICAL(3), OTHER(4);
	
	private int value;

    private ItemCategory(int value) {
    	this.value = value;
    }
    
    public int getValue() {
    	return this.value;
    }

}
