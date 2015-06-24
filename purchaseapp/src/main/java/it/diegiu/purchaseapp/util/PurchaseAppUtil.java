/**
 * 
 */
package it.diegiu.purchaseapp.util;

import it.diegiu.purchaseapp.data.Basket;
import it.diegiu.purchaseapp.data.Item;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoRes;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Diego Giudici
 *
 */
public class PurchaseAppUtil {
	
	private PurchaseAppUtil() {
		
	}
	
	public static String postProcessAddItems(PurchaseAppDtoRes dtoRes, String i) {
		
		StringBuilder builder = new StringBuilder();
		Basket basket = dtoRes.getBasket();
		
		builder.append("Output " + i + ":\n");
		for (Item item : basket.getItemList()) {
			builder.append(item.getName() + ": " + item.getPrice() + "\n");
		}
		
		builder.append("Sales Taxes: " + basket.getSalesTaxes() + "\n");
		builder.append("Total: " + basket.getPrices() + "\n");
		return builder.toString();
	}
	
	public static double roundPrice(double v, int newS, RoundingMode rm) {
	    return new BigDecimal(v).setScale(newS, rm).doubleValue();
	}

}
