package c4c.connect.citizenretail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import c4c.connect.citizenretail.model.Customer;
import c4c.connect.citizenretail.model.Order;
import c4c.connect.citizenretail.model.ShopInventory;
import c4c.connect.citizenretail.service.CustomerActivityService;

@RestController
@RequestMapping("/customer")
public class CustomerActivityController {

	@Autowired
	private CustomerActivityService customerActivityService;

	@PostMapping("/registration")
	public boolean registerCustomer(@RequestBody Customer customer) {
		customerActivityService.customerRegistration(customer);
		return true;
	}

	@GetMapping("/profile-retreive")
	public Customer getCustomer(@RequestParam String customerId) {
		return customerActivityService.getCustomer(customerId);
	}

	@PostMapping("/profile-update")
	public boolean updateCustomer(@RequestBody Customer customer) {
		customerActivityService.updateCustomer(customer);
		return true;
	}

	@GetMapping("/all-orders")
	public List<Order> getAllOrders(@RequestParam String customerId) {
		return customerActivityService.getAllOrdersOfUser(customerId);
	}

	@GetMapping("/order-details")
	public Order getOrderWithOrderid(@RequestParam String customerId, @RequestParam String orderId) {
		return customerActivityService.getOrderDetals(customerId, orderId);
	}

	@GetMapping("/active-orders")
	public List<Order> getActiveOrders(@RequestParam String customerId) {
		return customerActivityService.getAllActiveOrdersOfUser(customerId);
	}

	@PostMapping("/create-or-update-order")
	public Order createOrder(@RequestBody Order order) {
		return customerActivityService.createorUpdateOrder(order);
	}

	@GetMapping("/retrieve-shop-inventory")
	public ShopInventory getInventoryListForShop(@RequestParam String customerId, @RequestParam String shopId) {
		return customerActivityService.getShopInventory(customerId, shopId);
	}

//	@PostMapping("/save-shop-inventory")
//	public String saveInventoryListForShop(@RequestParam String customerId, @RequestParam String shopId,
//			@RequestBody ShopInventory shopInventory) {
//		return customerActivityService.saveShopInventory(shopInventory);
//	}

}
