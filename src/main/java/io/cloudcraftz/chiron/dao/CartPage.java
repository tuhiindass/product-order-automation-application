package io.cloudcraftz.chiron.dao;

import org.openqa.selenium.By;
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
public class CartPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private WebDriver webDriver;
	
	@Value("${application.cartPage.viewCart.xpath}")
	private String viewCartLinkXPath;
	
	@Value("${application.cartPage.checkout.xpath}")
	private String checkoutButtonXPath;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public void checkout() throws Exception {
		webDriver.findElement(By.xpath(viewCartLinkXPath)).click();
		Thread.sleep(2000);
		if(isDebugEnabled) {
			log.debug("View cart link has been clicked. Inside cart page now.");
		}
		webDriver.findElement(By.xpath(checkoutButtonXPath)).click();
		Thread.sleep(3000);
		if(isDebugEnabled) {
			log.debug("Checkout button has been clicked.");
		}
	}

}
