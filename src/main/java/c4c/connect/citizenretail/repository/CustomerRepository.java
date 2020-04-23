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
import c4c.connect.citizenretail.model.Customer;
import c4c.connect.citizenretail.model.Shop;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CustomerRepository {

	@Autowired
	private CloudantClient client;

	private Database db;

	@Value("${cloudant.db}")
	private String dbName;

	@PostConstruct
	private void setUp() {
		this.db = this.client.database(dbName, true);
	}

	public String save(Customer user) {
		Response response = db.post(user);
		String id = response.getId();
		return id;
	}
	
	public String update(Customer user) {
		Response response = db.update(user);
		String id = response.getId();
		return id;
	}

	public Customer findById(String id) {
		Customer user = db.find(Customer.class, id);
		return user;
	}

	public Customer findByUserId(String userId) {
		Selector sel = eq("userId", userId).eq("type", CitizenRetailConnectConstants.CUSTOMER_TYPE);
		QueryResult<Customer> customer = db.query(new QueryBuilder(sel).build(), Customer.class);
		if (customer.getDocs().isEmpty()) {
			log.error("No Data found for the customer id :" + userId);
			return new Customer();
		}
		return customer.getDocs().get(0);
	}

}
