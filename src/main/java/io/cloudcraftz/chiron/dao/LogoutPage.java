package io.cloudcraftz.chiron.dao;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */
@Slf4j
@Component
public class LogoutPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private WebDriver webDriver;
	
	@Value("${application.homePage.menu.xpath}")
	private String menuXPath;
	
	@Value("${application.homePage.logout.xpath}")
	private String logoutXPath;
	
	
	public void clickLogout() throws InterruptedException {
		try {
			webDriver.findElement(By.xpath(menuXPath)).click();
			Thread.sleep(2000);
			webDriver.findElement(By.xpath(logoutXPath)).click();
			if (isDebugEnabled) {
				log.debug("Logout has been clicked.");
			}
		} catch(NoSuchElementException ne) {
			log.debug("Not Logged out");
		}
	}

}
