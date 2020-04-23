package c4c.connect.citizenretail.model;

import c4c.connect.citizenretail.common.CitizenRetailConnectConstants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InventoryItem {
	private String _id;
	private String _rev;
	
	private String category;
	private String name;
	private String price;
	private String quantity;
	private String rate;
	private boolean availability;
	private boolean confirmed;
	
	@Setter(AccessLevel.NONE)
	private final String type = CitizenRetailConnectConstants.INVENTORY_ITEM_TYPE;
}
