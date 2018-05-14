package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Customer;

public class CustomerCRUD extends CRUDoperations {

	private static List<Customer> customers;

	public CustomerCRUD() {
		customers = new ArrayList<Customer>();
	}

	public boolean insertCustomer(Customer customer) throws SQLException {
		boolean result = false;
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("id", customer.getId());
		values.put("firstname", customer.getFirstName());
		values.put("lastname", customer.getLastName());

		DBTool.getConnection(dbType, server, db, usr, pwd);
		result = DBTool.insert("customers", values);
		DBTool.closeConnection();
		return result;
	}

	// Retrive customers' information from database and return a list
	public List<Customer> retrieveCustomers() throws SQLException {

		DBTool.getConnection(dbType, server, db, usr, pwd);
		ResultSet rs = DBTool.selectAll("customers");
		customers.clear();

		while (rs.next()) {
			Customer customer = new Customer();
			customer.setId(rs.getString("id"));
			customer.setFirstName(rs.getString("firstname"));
			customer.setLastName(rs.getString("lastname"));
			customers.add(customer);
		}
		DBTool.closeConnection();
		return customers;
	}
}
