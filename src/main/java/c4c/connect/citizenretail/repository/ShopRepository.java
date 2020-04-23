package c4c.connect.citizenretail.repository;

import static com.cloudant.client.api.query.Expression.eq;

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
import c4c.connect.citizenretail.model.Shop;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ShopRepository {

	@Autowired
	private CloudantClient client;

	private Database db;

	@Value("${cloudant.db}")
	private String dbName;

	@PostConstruct
	private void setUp() {
		this.db = this.client.database(dbName, true);
	}

	public String save(Shop shop) {
		Response response = db.post(shop);
		String id = response.getId();
		return id;
	}
	
	

	public Shop findById(String id) {
		Shop shop = db.find(Shop.class, id);
		return shop;
	}

	public Shop findByShopId(String shopId) {
		Selector sel = eq("shopId", shopId).eq("type", CitizenRetailConnectConstants.SHOP_TYPE);
		QueryResult<Shop> shop = db.query(new QueryBuilder(sel).build(), Shop.class);
		if (shop.getDocs().isEmpty()) {
			log.error("No Data found for the shop id :" + shopId);
			return new Shop();
		}
		return shop.getDocs().get(0);
	}

}
