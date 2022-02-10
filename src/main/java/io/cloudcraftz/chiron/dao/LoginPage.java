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
public class LoginPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();
	
	@Autowired
	private WebDriver webDriver;
	
	@Value("${application.loginPage.userName.xpath}")
	private String userNameXPath;
	
	@Value("${application.loginPage.password.xpath}")
	private String passwordXPath;
	
	@Value("${application.loginPage.login.xpath}")
	private String loginXPath;
	
	@Value("${application.user.id}")
	private String userId;
	
	@Value("${application.user.password}")
	private String password;
	
	/**
	 * 
	 */
	public void clickLogin() {
		try {
			webDriver.findElement(By.xpath(userNameXPath)).sendKeys(userId);
			webDriver.findElement(By.xpath(passwordXPath)).sendKeys(password);
			webDriver.findElement(By.xpath(loginXPath)).click();
			if (isDebugEnabled) {
				log.debug("Login has been clicked.");
			}
		} catch(NoSuchElementException ne) {
			log.debug("Already Logged in");
		}
	}


}
