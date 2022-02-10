/**
 * 
 */
package io.cloudcraftz.chiron.service;

import org.openqa.selenium.WebDriver;

/**
 * @author tuhin
 *
 */
public interface WebDriverProvider {
	/**
	 * @return {@link WebDriver} instance.
	 */
	WebDriver getWebDriver();
}
