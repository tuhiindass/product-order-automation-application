package io.cloudcraftz.chiron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cloudcraftz.chiron.service.ProductOrderService;
import io.cloudcraftz.chiron.vo.ProductOrderRequest;
import io.cloudcraftz.chiron.vo.ProductOrderResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tuhin
 *
 */
@Slf4j
@RestController
public class APIController {
	private final boolean isDebugEnabled = log.isDebugEnabled();

	@Autowired
	private ProductOrderService productOrderService;
	
	@GetMapping(path = "/ping")
	public String ping() {
		if(isDebugEnabled) {
			log.debug("Yes, I am alive.");
		}
		return "ALIVE";
	}
	
	@PostMapping(path = "/automate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductOrderResponse> automateOrder(@RequestBody ProductOrderRequest request) {
		ProductOrderResponse response = new ProductOrderResponse();
		try {
			productOrderService.automateOrder(request);
			response.setStatus("SUCCESS");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception ex) {
			log.error("Error in placing the order", ex);
			response.setStatus("ERROR");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
