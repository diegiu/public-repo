/**
 * 
 */
package it.diegiu.purchaseapp.test;

import it.diegiu.purchaseapp.configuration.PurchaseAppConfig;
import it.diegiu.purchaseapp.consumer.PurchaseAppConsumer;
import it.diegiu.purchaseapp.data.Item;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoReq;
import it.diegiu.purchaseapp.dto.PurchaseAppDtoRes;
import it.diegiu.purchaseapp.exception.PurchaseAppFaultException;
import it.diegiu.purchaseapp.service.PurchaseAppService;
import it.diegiu.purchaseapp.util.ItemCategory;
import it.diegiu.purchaseapp.util.PurchaseAppUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Diego Giudici
 *
 */
@Configuration
@ComponentScan(value="it.diegiu.purchaseapp.consumer")
public class PurcheseAppTest {
	
	private AnnotationConfigApplicationContext context;
	
	@Bean
    public PurchaseAppService getService() {
        return new PurchaseAppService() {
			public PurchaseAppDtoRes addItems(PurchaseAppDtoReq dtoReq)
					throws PurchaseAppFaultException {
				// TODO Auto-generated method stub
				return null;
			}
        };
    }
	
	@Before
    public void setUp() throws Exception {
        context = new AnnotationConfigApplicationContext(PurchaseAppConfig.class);
    }
     
    @After
    public void tearDown() throws Exception {
        context.close();
    }
 
    @Test
    public void testApp() {
    	
    	PurchaseAppDtoRes dtoRes = null;
    	List<Item> itemList;
    	
    	try {
    		PurchaseAppConsumer appConsumer = context.getBean(PurchaseAppConsumer.class);
    		
    		itemList = new ArrayList<Item>(Arrays.asList(
    			new Item("book", 12.49, ItemCategory.BOOKS.getValue()), 
    			new Item("music CD", 14.99, ItemCategory.OTHER.getValue()), 
    			new Item("chocolate bar", 0.85, ItemCategory.FOOD.getValue())));
    		
    		dtoRes = appConsumer.processAddItems(new PurchaseAppDtoReq(itemList));
    		System.out.println(PurchaseAppUtil.postProcessAddItems(dtoRes, "1"));
    		
    		itemList = new ArrayList<Item>(Arrays.asList(
    			new Item("imported box of chocolates", 10.00, ItemCategory.FOOD.getValue(), Boolean.TRUE), 
    			new Item("imported bottle of perfume", 47.50, ItemCategory.OTHER.getValue(), Boolean.TRUE)));
    		
    		dtoRes = appConsumer.processAddItems(new PurchaseAppDtoReq(itemList));
    		System.out.println(PurchaseAppUtil.postProcessAddItems(dtoRes, "2"));
    		
    		itemList = new ArrayList<Item>(Arrays.asList(
    			new Item("imported bottle of perfume", 27.99, ItemCategory.OTHER.getValue(), Boolean.TRUE), 
    			new Item("bottle of perfume", 18.99, ItemCategory.OTHER.getValue()), 
    			new Item("packet of headache pills", 9.74, ItemCategory.MEDICAL.getValue()),
    			new Item("imported box of chocolates", 11.25, ItemCategory.FOOD.getValue(), Boolean.TRUE)));
    		
    		dtoRes = appConsumer.processAddItems(new PurchaseAppDtoReq(itemList));
    		System.out.println(PurchaseAppUtil.postProcessAddItems(dtoRes, "3"));
    		
    		Assert.assertNotNull(appConsumer.processAddItems(new PurchaseAppDtoReq(itemList)));
    		
    	} catch (PurchaseAppFaultException pfe) {
			System.err.println(pfe.getMessage());
			pfe.printStackTrace();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
    }
}
