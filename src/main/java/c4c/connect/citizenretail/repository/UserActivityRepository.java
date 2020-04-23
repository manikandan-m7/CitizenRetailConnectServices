package c4c.connect.citizenretail.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

@Repository
public class UserActivityRepository {

	@Autowired
	private CloudantClient client;

	private Database db;

	@Value("${cloudant.db}")
	private String dbName;

	@PostConstruct
	private void setUp() {
		this.db = this.client.database(dbName, true);
	}



}
