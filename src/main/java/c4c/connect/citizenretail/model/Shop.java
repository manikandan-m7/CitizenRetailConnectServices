package c4c.connect.citizenretail.model;

import java.time.LocalDateTime;
import java.util.List;

import c4c.connect.citizenretail.common.CitizenRetailConnectConstants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Shop {

	private String _id;
	private String _rev;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final String type = CitizenRetailConnectConstants.SHOP_TYPE;
	
	private String emailId;
	private String password;
	private Integer active = 1;
	private boolean isEnabled = true;
	private String mobileNumber;
	private String[] location;
	private String Address;
	
	private String shopId;
	private String distance;
	private String shopName;
	private LocalDateTime openingTime;
	private LocalDateTime closingTime;
	private LocalDateTime preferredDeliveryTime;
	private String upiId;
	private List<String> paymentOptionsSupported;

	private List<InventoryItem> inventoryItems;

}
