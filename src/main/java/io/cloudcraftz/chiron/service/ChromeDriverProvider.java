/**
 * 
 */
package io.cloudcraftz.chiron.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */
@Slf4j
@Component("chromeDriverProvider")
public class ChromeDriverProvider implements WebDriverProvider {
	private final boolean isDebugEnabled = log.isDebugEnabled();
	
	@Getter
	private WebDriver webDriver;
	
	private ChromeOptions getChromeOptions() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1580,1280");
		chromeOptions.addArguments("--incognito");

        final Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

	@PostConstruct
	private void createDriver() {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver(getChromeOptions());
	}
	
	@PreDestroy
	private void releaseDriver() {
		if(webDriver != null) {
			webDriver.quit();
			if(isDebugEnabled) {
				log.debug("Web driver resource has been released.");
			}
		}
	}

}
