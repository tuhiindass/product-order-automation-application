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
public class PaymentPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private WebDriver webDriver;
	
	@Value("${application.paymentPage.itemTotalAmount.xpath}")
    private String itemTotalAmountXPath;
	
	@Value("${application.paymentPage.itemTaxAmount.xpath}")
    private String itemTaxAmountXPath;
	
	@Value("${application.paymentPage.finalTotalAmount.xpath}")
    private String finalTotalAmountXPath;
	
	@Value("${application.paymentPage.finishButton.xpath}")
    private String finishButtonXPath;
	
	public void processWithPayment() {
		 //log the order amount details
        if (isDebugEnabled) {
            log.debug("Item Total Amount:{}",
                    webDriver.findElement(By.xpath(itemTotalAmountXPath)).getText());
            log.debug("Item tax Amount:{}",
                    webDriver.findElement(By.xpath(itemTaxAmountXPath)).getText());
            log.debug("Final Total Amount:{}",
                    webDriver.findElement(By.xpath(finalTotalAmountXPath)).getText());
        }
        
        webDriver.findElement(By.xpath(finishButtonXPath)).click();
        if (isDebugEnabled) {
			log.debug("Payment Done....");
		}
	}

}
