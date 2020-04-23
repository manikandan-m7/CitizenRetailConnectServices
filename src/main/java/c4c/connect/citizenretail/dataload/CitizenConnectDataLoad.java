package c4c.connect.citizenretail.dataload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import c4c.connect.citizenretail.model.Customer;
import c4c.connect.citizenretail.model.InventoryItem;
import c4c.connect.citizenretail.model.Order;
import c4c.connect.citizenretail.model.OrderStatus;
import c4c.connect.citizenretail.model.ShopInventory;
import c4c.connect.citizenretail.repository.CustomerRepository;
import c4c.connect.citizenretail.repository.OrderRepository;
import c4c.connect.citizenretail.repository.ShopInventoryRepository;

public class CitizenConnectDataLoad  implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ShopInventoryRepository shopInventoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	// @Override
	public void run(String... args) throws Exception {

		Customer customer = new Customer();
		customer.setActive(1);
		customer.setEnabled(true);
		customer.setFirstName("TestUser1");
		customer.setMobileNumber("9898123123");
		customer.setUserId("user01");

		customerRepository.save(customer);
		System.out.println("Saved customer");

		Customer retailer = new Customer();
		retailer.setActive(1);
		retailer.setEnabled(true);
		retailer.setFirstName("TestRetailer1");
		retailer.setMobileNumber("989998989");
		retailer.setUserId("retailer01");

		customerRepository.save(retailer);
		System.out.println("Saved retailer :" + retailer.getUserId());

		Customer retailer1 = new Customer();
		retailer1.setActive(1);
		retailer1.setEnabled(true);
		retailer1.setFirstName("TestRetailer2");
		retailer1.setMobileNumber("98944449");
		retailer1.setUserId("retailer02");

		customerRepository.save(retailer1);
		System.out.println("Saved retailer :" + retailer1.getUserId());

		ShopInventory shopInventry = new ShopInventory();
		shopInventry.setShopId(retailer.getUserId());
		List<InventoryItem> inItems = new ArrayList<InventoryItem>();
		InventoryItem inItem = null;
		for (int i = 0; i <= 3; i++) {
			inItem = new InventoryItem();
			inItem.setCategory("CAT0" + i);
			//inItem.setId(i + "");
			inItem.setName("InventoryItem-" + i);
			inItem.setAvailability(true);
			inItems.add(inItem);
		}
		shopInventry.setInventoryItems(inItems);
		shopInventoryRepository.save(shopInventry);
		System.out.println("Saved inventories for " + shopInventry.getShopId());

		ShopInventory shopInventry1 = new ShopInventory();
		shopInventry1.setShopId(retailer1.getUserId());
		List<InventoryItem> inItems1 = new ArrayList<InventoryItem>();
		InventoryItem inItem1 = null;
		for (int i = 10; i <= 13; i++) {
			inItem1 = new InventoryItem();
			inItem1.setCategory("CAT0" + i);
			//inItem1.setId(i + "");
			inItem1.setName("InventoryItem-" + i);
			inItem1.setAvailability(true);
			inItems1.add(inItem1);
		}
		shopInventry1.setInventoryItems(inItems1);
		shopInventoryRepository.save(shopInventry1);
		System.out.println("Saved inventories for " + shopInventry1.getShopId());

		Order order = new Order();
		order.setOrderId("1");
		order.setOrderStatus(OrderStatus.NEW.toString());
		order.setShopId(retailer.getUserId());
		order.setUserId(customer.getUserId());
		order.setOrderedItems(inItems1);

		orderRepository.save(order);
		System.out.println("Saved order 1");

		Order order1 = new Order();
		order1.setOrderId("2");
		order1.setOrderStatus(OrderStatus.COMPLETED.toString());
		order1.setShopId(retailer1.getUserId());
		order1.setUserId(customer.getUserId());
		order1.setOrderedItems(inItems1);

		orderRepository.save(order1);
		System.out.println("Saved order 2");

		Order order2 = new Order();
		order2.setOrderId("3");
		order2.setOrderStatus(OrderStatus.NEW.toString());
		order2.setShopId(retailer.getUserId());
		order2.setUserId(retailer1.getUserId());
		order2.setOrderedItems(inItems1);

		orderRepository.save(order2);
		System.out.println("Saved order 3");
	}

}
