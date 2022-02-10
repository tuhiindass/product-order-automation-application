package io.cloudcraftz.chiron.dao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.cloudcraftz.chiron.vo.Address;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */
@Slf4j
@Component
public class AddressPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private WebDriver webDriver;

	@Value("${application.addressPage.firstName.xpath}")
	private String firstNameXPath;

	@Value("${application.addressPage.lastName.xpath}")
	private String lastNameXPath;

	@Value("${application.addressPage.zipCode.xpath}")
	private String zipCodeXPath;
	
	 @Value("${application.addressPage.addContinueButton.xpath}")
	    private String addContinueButtonXPath;

	/**
	 *
	 * @param address
	 */
	public void addNewAddress(Address address) {
		webDriver.findElement(By.xpath(firstNameXPath)).sendKeys(address.getFirstName());
		webDriver.findElement(By.xpath(lastNameXPath)).sendKeys(address.getLastName());
		webDriver.findElement(By.xpath(zipCodeXPath)).sendKeys(address.getZipCode());
		webDriver.findElement(By.xpath(addContinueButtonXPath)).click();
		if (isDebugEnabled) {
			log.debug("'Add Address' button has been clicked.");
			log.debug("FirstName: "+address.getFirstName()+" LastName: "+address.getLastName());
		}
	}
}
