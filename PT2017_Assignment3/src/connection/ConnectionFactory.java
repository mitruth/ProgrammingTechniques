package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName(), null);
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/orderManagement?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "dan1234";
	
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	private ConnectionFactory() {//connection to the DB will be placed
		//in a Singleton object
		try {
			Class.forName(DRIVER);//it allows you to use the Driver class without having an explicit import for your class. 
			//This allows you to build the project without having to have the Oracle driver in your classpath
			//Returns the Class object associated with the class or interface with the given string name.
		} catch (ClassNotFoundException e) {
			//no definition for the class with the specified name could be found.
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {//A connection (session) with a specific database.
		//SQL statements are executed and results are returned within the context of a connection.
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}
	
	public static void close (Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}
	
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}
	
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}

}
