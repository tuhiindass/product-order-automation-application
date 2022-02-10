package io.cloudcraftz.chiron.service;

import org.openqa.selenium.WebDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.cloudcraftz.chiron.dao.AddressPage;
import io.cloudcraftz.chiron.dao.CartPage;
import io.cloudcraftz.chiron.dao.LoginPage;
import io.cloudcraftz.chiron.dao.LogoutPage;
import io.cloudcraftz.chiron.dao.PaymentPage;
import io.cloudcraftz.chiron.dao.ProductPage;
import io.cloudcraftz.chiron.vo.Address;
import io.cloudcraftz.chiron.vo.ProductOrderRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */

@Slf4j
@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    private final boolean isDebugEnabled = log.isDebugEnabled();
    
    @Autowired
    private WebDriver webDriver;
    
    @Value("${application.url}")
    private String applicationUrl;
    
    @Autowired
    private LoginPage loginPage;
    
    @Autowired
    private ProductPage productPage;
    
    @Autowired
    private CartPage cartPage;
    
    @Autowired
    private AddressPage addressPage;
    
    @Autowired
    private PaymentPage paymentPage;
    
    @Autowired
    private LogoutPage logoutPage;
    
 // address will be required 2 times - adding address and checkout
    Address address = new Address();
    
    //TODO put an outer catch block to catch all unhandled exceptions and send back as response
    public void automateOrder(ProductOrderRequest request) throws Exception {

        //clear cookies
        webDriver.manage().deleteAllCookies();


        // open webpage
        webDriver.get(applicationUrl);
        if (isDebugEnabled) {
            log.debug("Inside home page now.");
        }
        
        //Login
        loginPage.clickLogin();
        if (isDebugEnabled) {
            log.debug("Logged in successfully. Inside accounts page now.");
        }
        
        Thread.sleep(3000);
        
        
        //addtoCart
//        productPage.addToCart();
//        if (isDebugEnabled) {
//            log.debug("Products have been successfully added to the cart.");
//        }
        
        //search product and add to cart
        productPage.searchAndAddToCart(request.getProducts());
        if (isDebugEnabled) {
            log.debug("Products have been successfully added to the cart.");
        }
        
        Thread.sleep(3000);
        //cartCheckout
        cartPage.checkout();
        if (isDebugEnabled) {
            log.debug("The cart has been checked out successfully.");
        }
        
        
        address.setFirstName(request.getRecipientFirstName());
        address.setLastName(request.getRecipientLastName());
        address.setZipCode(String.valueOf(request.getDeliveryZipCode()));
        
        //address add
        addressPage.addNewAddress(address);
        if (isDebugEnabled) {
            log.debug("Address has been added successfully.");
        }
        Thread.sleep(3000);
        
        //payment
        paymentPage.processWithPayment();
        if (isDebugEnabled) {
            log.debug("Payment has been successfully.");
            log.debug("THANK YOU FOR YOUR ORDER");          
        }
        Thread.sleep(3000);
        
        //logout
        logoutPage.clickLogout();;
        if (isDebugEnabled) {
            log.debug("Logged out successfully !");
        }
        

        
    }

}
