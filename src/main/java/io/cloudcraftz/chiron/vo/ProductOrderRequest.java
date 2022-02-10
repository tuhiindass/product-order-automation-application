/**
 * 
 */
package io.cloudcraftz.chiron.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tuhin
 *
 */
@Getter
@Setter
@ToString
public class ProductOrderRequest {
	private String orderNo;
	List<Product> products;
	private String recipientFirstName;
	private String recipientLastName;
	private int deliveryZipCode;
}
