package it.diegiu.weather.bulletin.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The user navigation controller of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
@Controller
public class ApplicationController implements ApplicationContextAware {
	
	private ApplicationContext context;
	@Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
    }
	
	/**
	 * The endpoint (entry point) method that adds attributes to the model and redirect to 
	 * the dedicated view.
	 * 
	 * @param model the holder for model attributes
	 * @return the name of the dedicated view
	 * 
	 * @author giudicidiego
	 */
	@GetMapping("/")
    public String applicationForm(Model model) {
    	return "form";
    }
	
	/**
	 * The endpoint method that executes the shutdown of the application.
	 * 
	 * @author giudicidiego
	 */
    @PostMapping("/shutdown-context")
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }
}