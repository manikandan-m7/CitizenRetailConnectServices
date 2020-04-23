package c4c.connect.citizenretail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c4c.connect.citizenretail.model.Customer;
import c4c.connect.citizenretail.model.Order;
import c4c.connect.citizenretail.model.ShopInventory;
import c4c.connect.citizenretail.repository.CustomerRepository;
import c4c.connect.citizenretail.repository.OrderRepository;
import c4c.connect.citizenretail.repository.ShopInventoryRepository;
import c4c.connect.citizenretail.repository.UserActivityRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerActivityService {

	@Autowired
	private UserActivityRepository userActivityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ShopInventoryRepository shopInventoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	public String customerRegistration(Customer customer) {
		String savedUser = customerRepository.save(customer);
		log.info(customer.getUserId() + "| Registered successfully with id : " + savedUser);
		return savedUser;
	}

	public Customer getCustomer(String userId) {
		return customerRepository.findByUserId(userId);
	}

	public void updateCustomer(Customer customer) {
		Customer cust = customerRepository.findByUserId(customer.getUserId());
		if (null != cust) {
			customer.set_id(cust.get_id());
			customer.set_rev(cust.get_rev());
			customerRepository.update(customer);
		}
	}

	public List<Order> getAllOrdersOfUser(String customerId) {
		return orderRepository.findByUserId(customerId);
	}

	public Order getOrderDetals(String customerId, String orderId) {
		return orderRepository.findByCustomerIdAndOrderId(customerId, orderId);
	}

	public List<Order> getAllActiveOrdersOfUser(String customerId) {
		return orderRepository.findByOrderStatusAndUserId(customerId);
	}

	public Order createorUpdateOrder(Order order) {
		Order oldOrder = orderRepository.findByOrderId(order.getOrderId());
		if (null != oldOrder) {
			order.set_id(oldOrder.get_id());
			order.set_rev(oldOrder.get_rev());
			return orderRepository.update(order);
		}
		return orderRepository.save(order);
	}

	public ShopInventory getShopInventory(String customerId, String shopId) {
		ShopInventory shopInventories = shopInventoryRepository.findByShopId(shopId);
		return shopInventories;
	}

//	public String saveShopInventory(ShopInventory shopInventory) {
//		log.info(shopInventory.getShopId() + "| Registering  shop inventories ");
//		String shopInventoryId = shopInventoryRepository.save(shopInventory);
//		return shopInventoryId;
//	}

}
