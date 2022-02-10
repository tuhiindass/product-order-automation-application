/**
 * 
 */
package io.cloudcraftz.chiron.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.cloudcraftz.chiron.service.WebDriverProvider;

/**
 * @author tuhin
 *
 */
@Configuration
public class AppConfig {
	@Autowired
	@Qualifier(value = "chromeDriverProvider")
	private WebDriverProvider webDriverProvider;
	
	@Bean
    public WebDriver webDriver() {
		return webDriverProvider.getWebDriver();
    }
}
