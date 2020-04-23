package c4c.connect.citizenretail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.CloudantClient;

@RestController
public class HealthCheckController {
	@Autowired
	private CloudantClient cloudantClient;

	@GetMapping("/cloudant-health")
	public String cloudantHealthCheck() {
		List<String> cloudantDbs;
		try {
			cloudantDbs = cloudantClient.getAllDbs();
		} catch (NullPointerException e) {
			return "Cloudant Connection failed";
		}
		return "Connected. Found the dbs : " + cloudantDbs.toString();
	}
}
