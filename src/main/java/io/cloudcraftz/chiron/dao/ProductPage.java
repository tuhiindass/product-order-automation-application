package io.cloudcraftz.chiron.dao;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.cloudcraftz.chiron.vo.Product;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */

@Slf4j
@Component
public class ProductPage {
	private final boolean isDebugEnabled = log.isDebugEnabled();
	
	@Autowired
	private WebDriver webDriver;
	
//	@Value("${application.productsPage.productSearch.xpath}")
//	private String productXPath;
	
	@Value("${application.productPage.productSearch.xpath}")
	private String productSearchXPath;
	
	@Value("${application.productPage.addToCart.xpath}")
	private String addToCartXPath;
	
	
//	public void addToCart() {
//		webDriver.findElement(By.xpath(addToCartXPath)).click();
//		if(isDebugEnabled) {
//			log.debug("Product Sauce Labs Backpack has been added to the cart.");
//		}
//
//	}
	
	public void searchAndAddToCart(List<Product> products) throws Exception {
		if((products != null) && !products.isEmpty()) {
			List<Product> missingProducts = null;
			boolean yesContinue = true;
			
			while(yesContinue) {
				missingProducts = new ArrayList<>(products.size());
				for(Product product : products) {
					boolean productFound = false;
					//search product
					String productName = product.getItem();
					String prdSrchXPath = productSearchXPath.replace("{productName}", productName);
					if(isDebugEnabled) {
						log.debug("Going to search for product: {} with xpath: {}", productName, prdSrchXPath);
					}
					try {
						webDriver.findElement(By.xpath(prdSrchXPath)).click();
						productFound = true;
						Thread.sleep(2000);
					} catch(NoSuchElementException ne) {
						if(isDebugEnabled) {
							log.debug("Product {} hasn't been found in this page.", productName);
						}
						//add to list of missing products
						//missingProducts.add(product);
					}
					if(productFound) {
						//add to cart
						if(isDebugEnabled) {
							log.debug("Product {} is being added to cart.", productName);
						}
						//webDriver.findElement(By.xpath(addToCartXPath)).click();
						
							webDriver.findElement(By.xpath(addToCartXPath)).click();
							if(isDebugEnabled) {
//								WebElement we = webDriver.findElement(By.xpath(cartQuantityXPath)).findElement(By.tagName("span"));
//								log.debug("Items count on cart ={}.",
//										we.getAttribute("aria-label"));
								log.debug("Add to cart is clicked for product {}", productName);
							}
							webDriver.findElement(By.xpath("//*[@id='back-to-products']")).click();

						
						Thread.sleep(1000);
						if(isDebugEnabled) {
							log.debug("Product {} has been added to the cart.", productName);
						}

			
					}
				}
				
				if(!missingProducts.isEmpty()) {
					//let's check on next page
					try {
						products = missingProducts;
						Thread.sleep(2000);
						if(isDebugEnabled) {
							log.debug("Going to search next page for products yet to be found.");
						}
					} catch(NoSuchElementException ne) {
						//no more page
						if(isDebugEnabled) {
							log.debug("No more pages of products.");
						}
						for(Product missingProduct : missingProducts) {
							log.error("Product '{}' couldn't be found.", missingProduct.getItem());
						}
						//let's stop as there isn't next page for products any more
						yesContinue = false;
					}
				} else {
					//all products have been found
					if(isDebugEnabled) {
						log.debug("No more missing products!");
					}
					yesContinue = false;
				}
			}
		} else {
			log.error("Can't work with null or empty product list.");
		}
	}
	
	
	

}
