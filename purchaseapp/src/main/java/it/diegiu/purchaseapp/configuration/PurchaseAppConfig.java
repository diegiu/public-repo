/**
 * 
 */
package it.diegiu.purchaseapp.configuration;

import it.diegiu.purchaseapp.service.PurchaseAppService;
import it.diegiu.purchaseapp.service.PurchaseAppServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Diego Giudici
 *
 */
@Configuration
@ComponentScan(value={"it.diegiu.purchaseapp.consumer"})
public class PurchaseAppConfig {
	
	@Bean
    public PurchaseAppService getService() {
        return new PurchaseAppServiceImpl();
    }

}
