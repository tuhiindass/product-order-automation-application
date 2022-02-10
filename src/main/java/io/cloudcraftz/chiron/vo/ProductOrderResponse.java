/**
 *
 */
package io.cloudcraftz.chiron.vo;

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
public class ProductOrderResponse {
    //TODO we need to add more information on the response object
    //e.g. order total, generated order id, order date/time etc
    private String status;
}
