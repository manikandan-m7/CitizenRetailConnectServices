package c4c.connect.citizenretail.repository;

import static com.cloudant.client.api.query.Expression.eq;
import static com.cloudant.client.api.query.Operation.and;
import static com.cloudant.client.api.query.Operation.or;

import java.util.ArrayList;
import java.util.List;

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
import c4c.connect.citizenretail.model.Order;
import c4c.connect.citizenretail.model.OrderStatus;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OrderRepository {

	@Autowired
	private CloudantClient client;

	private Database db;

	@Value("${cloudant.db}")
	private String dbName;

	@PostConstruct
	private void setUp() {
		this.db = this.client.database(dbName, true);
	}

	public Order save(Order order) {
		Response response = db.save(order);
		order.set_id(response.getId());
		order.set_rev(response.getRev());
		return order;
	}
	
	public Order update(Order order) {
		Response response = db.update(order);
		order.set_id(response.getId());
		order.set_rev(response.getRev());
		return order;
	}

	public Order findById(String id) {
		Order order = db.find(Order.class, id);
		return order;
	}

	public Order findByOrderId(String orderId) {
		Selector sel = and(
				eq("orderId", orderId),
				eq("type", CitizenRetailConnectConstants.ORDER_TYPE));

		QueryResult<Order> order = db.query(new QueryBuilder(sel).build(), Order.class);
		if (order.getDocs().isEmpty()) {
			log.error("No Data found for the order id :" + orderId);
			return new Order();
		}
		return order.getDocs().get(0);
	}

	public List<Order> findByOrderStatusAndUserId(String userId) {
		Selector sel = and(
				or(eq("orderStatus", OrderStatus.NEW.toString()), eq("orderStatus", OrderStatus.CONFIRMED.toString()),
						eq("orderStatus", OrderStatus.PARTIALLY_ACCEPTED.toString()),
						eq("orderStatus", OrderStatus.RECONFIRMED.toString())),
				eq("type", CitizenRetailConnectConstants.ORDER_TYPE));
		QueryResult<Order> order = db.query(new QueryBuilder(sel).build(), Order.class);
		if (order.getDocs().isEmpty()) {
			log.error("No Data found for the user id :" + userId);
			return new ArrayList<>();
		}
		return order.getDocs();
	}

	public List<Order> findByUserId(String userId) {
		Selector sel = and(
				eq("userId", userId),
				eq("type", CitizenRetailConnectConstants.ORDER_TYPE));

		QueryResult<Order> order = db.query(new QueryBuilder(sel).build(), Order.class);
		if (order.getDocs().isEmpty()) {
			log.error("No Order Data found for the user id :" + userId);
			return new ArrayList<>();
		}
		return order.getDocs();
	}

	public Order findByCustomerIdAndOrderId(String customerId, String orderId) {
		Selector sel = and(eq("userId", customerId), eq("orderId", orderId),
				eq("type", CitizenRetailConnectConstants.ORDER_TYPE));

		QueryResult<Order> order = db.query(new QueryBuilder(sel).build(), Order.class);
		if (order.getDocs().isEmpty()) {
			log.error("No Order Data found for the user id :" + customerId);
			return new Order();
		}
		return order.getDocs().get(0);
	}
}
