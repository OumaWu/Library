package Persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Customer;

public class CustomerCRUD {

	private int dbType;
	private String server, db, usr, pwd;
	private static List<Customer> customers = new ArrayList<Customer>();

	public CustomerCRUD(int dbType, String server, String db, String usr, String pwd) {
		this.dbType = dbType;
		this.server = server;
		this.db = db;
		this.usr = usr;
		this.pwd = pwd;
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
