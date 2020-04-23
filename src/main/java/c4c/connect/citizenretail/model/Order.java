package c4c.connect.citizenretail.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import c4c.connect.citizenretail.common.CitizenRetailConnectConstants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Order {

	private String _id;
	private String _rev;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final String type = CitizenRetailConnectConstants.ORDER_TYPE;
	
	private String orderId;
	private String orderStatus;
	private String quantity;
	private String userId;
	private String shopId;
	private LocalDateTime orderTimeStamp;
	private String denialReason;
	private BigDecimal totalPrice;
	private String paymentMode;
	private LocalDateTime pickupOrDeliveryTime;
	private String deliveryMode;
	private List<InventoryItem> orderedItems;
}
