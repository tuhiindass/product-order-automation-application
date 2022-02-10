package io.cloudcraftz.chiron.service;

import io.cloudcraftz.chiron.vo.ProductOrderRequest;

/**
 * @author tuhin
 *
 */

public interface ProductOrderService {
	
	public void automateOrder(ProductOrderRequest request)throws Exception;

}
