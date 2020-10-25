package it.diegiu.weather.bulletin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import it.diegiu.weather.bulletin.model.WeatherReq;
import it.diegiu.weather.bulletin.model.WeatherRes;
import it.diegiu.weather.bulletin.orchestration.ApplicationOrchestration;

/**
 * Rest controller test class of the "tiny-weather-bulletin" project.
 * 
 * @author giudicidiego
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationEndpointTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
	private ApplicationOrchestration applicationOrchestration;
	
	@Test
    public void testGetWeatherData() throws Exception {
		WeatherRes weatherRes = new WeatherRes("Naples",0 , 0, 0, 0, 0, 0, 0, 0);
		when(applicationOrchestration.getWeatherData(new WeatherReq("Napoli", 9, 18))).thenReturn(weatherRes);
		this.mockMvc.perform(get("/weather/bulletin/averages?cityName=Napoli&fromHours=9&toHours=18")).andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}
}