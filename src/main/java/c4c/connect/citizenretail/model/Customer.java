package c4c.connect.citizenretail.model;

import java.time.LocalDateTime;

import c4c.connect.citizenretail.common.CitizenRetailConnectConstants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Customer{

	private String _id;
	private String _rev;
	
	private String firstName;
	private String lastName;
	private LocalDateTime preferredDeliveryTime;
	private String upiId;
	private String modeOfPayment;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final String type = CitizenRetailConnectConstants.CUSTOMER_TYPE;
	
	private String userId;
	private String emailId;
	private String password;
	private Integer active = 1;
	private boolean isEnabled = true;
	private String mobileNumber;
	private String[] location;
	private String Address;
}