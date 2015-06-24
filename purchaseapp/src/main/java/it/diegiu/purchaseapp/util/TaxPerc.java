/**
 * 
 */
package it.diegiu.purchaseapp.util;

/**
 * @author Diego Giudici
 *
 */
public enum TaxPerc {
	
	FIVE(5), TEN(10), FIFTEEN(15);
	
	private int value;

    private TaxPerc(int value) {
    	this.value = value;
    }
    
    public int getValue() {
    	return this.value;
    }

}
