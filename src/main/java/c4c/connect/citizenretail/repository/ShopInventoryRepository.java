package c4c.connect.citizenretail.repository;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;

import c4c.connect.citizenretail.common.CitizenRetailConnectConstants;
import c4c.connect.citizenretail.model.Order;
import c4c.connect.citizenretail.model.ShopInventory;
import c4c.connect.citizenretail.model.ShopInventory;
import c4c.connect.citizenretail.model.ShopInventory;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ShopInventoryRepository {

	@Autowired
	private CloudantClient client;

	private Database db;

	@Value("${cloudant.db}")
	private String dbName;

	@PostConstruct
	private void setUp() {
		this.db = this.client.database(dbName, true);
	}

	public String save(ShopInventory shopInventory) {
		Response response = db.post(shopInventory);
		String id = response.getId();
		return id;
	}

	public String update(ShopInventory shopInventory) {
		Response response = db.update(shopInventory);
		return response.getId();
	}

	public ShopInventory findById(String id) {
		ShopInventory shopInventory = db.find(ShopInventory.class, id);
		return shopInventory;
	}

	public ShopInventory findByShopId(String shopId) {
		Selector sel = and(
				eq("shopId", shopId),
				eq("type", CitizenRetailConnectConstants.SHOP_INVENTORY_TYPE)
				);
		QueryResult<ShopInventory> shopInventory = db.query(new QueryBuilder(sel).build(), ShopInventory.class);
		if (shopInventory.getDocs().isEmpty()) {
			log.error("No Inventory Data found for the shop id :" + shopId);
			return new ShopInventory();
		}
		return shopInventory.getDocs().get(0);
	}
}
