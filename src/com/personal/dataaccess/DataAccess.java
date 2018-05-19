package com.personal.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.personal.utils.ConfigurationLoader;

public class DataAccess {
	protected Connection connection;

	public DataAccess() throws Exception {
		if (connection == null) {
			connectDB();
		}
	}

	protected void connectDB() throws Exception {

		String username = null, password = null, dburl = null, driver;

		// TODO ConfigurationLoader shouldn't be referenced here, makes it tightly coupled
		ConfigurationLoader loader = new ConfigurationLoader();
		username = loader.getNodeAttribValue("database-settings", "username");
		password = loader.getNodeAttribValue("database-settings", "password");
		dburl = loader.getNodeAttribValue("database-settings", "dburl");
		driver = loader.getNodeAttribValue("database-settings", "driver");
		Class.forName(driver);
		connection = (Connection) DriverManager.getConnection(dburl, username, password);
	}

	public ResultSet executeQuery(String query) throws Exception {

		if (connection == null) {
			connectDB();
		}
		Statement statement = connection.createStatement();
		System.out.println(query);
		return statement.executeQuery(query);
	}
	

	public int updateQuery(String query) throws Exception {
		if (connection == null) {
			connectDB();
		}

		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		System.out.println(query);

		statement.executeUpdate();

		ResultSet rs = statement.getGeneratedKeys();

		int result = -1;

		if (rs.next()) {
			result = rs.getInt(1);
		}
		return result;
	}

	@Override
	public void finalize() throws SQLException {
		if (connection.isClosed()) {
			connection.close();
		}
	}
}
