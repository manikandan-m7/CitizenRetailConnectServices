package c4c.connect.citizenretail.model;

import java.util.List;

import c4c.connect.citizenretail.common.CitizenRetailConnectConstants;
import lombok.Data;

@Data
public class ShopInventory {
	private String _id;
	private String _rev;
	private String shopId;
	private List<InventoryItem> inventoryItems;
	private final String type = CitizenRetailConnectConstants.SHOP_INVENTORY_TYPE;
}
