/**
 * 
 */
package it.diegiu.purchaseapp.service;

import it.diegiu.purchaseapp.data.Basket;
import it.diegiu.purchaseapp.data.Item;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoReq;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoRes;
import it.diegiu.purchaseapp.exception.PurchaseAppFaultException;
import it.diegiu.purchaseapp.util.ItemCategory;
import it.diegiu.purchaseapp.util.PurchaseAppUtil;
import it.diegiu.purchaseapp.util.TaxPerc;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Giudici
 *
 */
public class PurchaseAppServiceImpl implements PurchaseAppService {

	public PurchaseAppDtoRes addItems(PurchaseAppDtoReq dtoReq) throws PurchaseAppFaultException {
		
		PurchaseAppDtoRes dtoRes = null;
		List<Item> modItemList = new ArrayList<Item>();
		
		double sTax;
		double sTaxes = 0;
		double prices = 0;
		
		try {
			for (Item item : dtoReq.getItemList()) {
				if (item.isImported()) {
					if (ItemCategory.OTHER.getValue() == item.getCategory()) {
						sTax = item.getPrice() * TaxPerc.FIFTEEN.getValue() / 100;
						item.setSalesTax(sTax);
						item.setPrice(item.getPrice() + sTax);
					} else {
						sTax = item.getPrice() * TaxPerc.FIVE.getValue() / 100;
						item.setSalesTax(sTax);
						item.setPrice(item.getPrice() + sTax);
					}
				}
				else {
					if (ItemCategory.OTHER.getValue() == item.getCategory()) {
						sTax = item.getPrice() * TaxPerc.TEN.getValue() / 100;
						item.setSalesTax(sTax);
						item.setPrice(item.getPrice() + sTax);
					}
				}
				
				item.setSalesTax(PurchaseAppUtil.roundPrice(item.getSalesTax(), 2, RoundingMode.HALF_UP));
				sTaxes += item.getSalesTax();
				item.setPrice(PurchaseAppUtil.roundPrice(item.getPrice(), 2, RoundingMode.HALF_UP));
				prices += item.getPrice();
				modItemList.add(item);
			}
			
			Basket basket = new Basket();
			basket.setItemList(modItemList);
			basket.setSalesTaxes(sTaxes);
			basket.setPrices(prices);
			dtoRes = new PurchaseAppDtoRes(basket);
			
		} catch (Exception e) {
			throw new PurchaseAppFaultException(e.getMessage(), e);
		}
		return dtoRes;
	}

}
