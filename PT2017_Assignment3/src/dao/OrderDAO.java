package dao;

import java.util.logging.Logger;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import connection.ConnectionFactory;
import model.Order;

public class OrderDAO {

	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO Orders (ID, productID, quantity)"
			+ " VALUES (?,?,?)";
	
	public static void insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;

		try{
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, order.getClientOrderID());
			insertStatement.setInt(2, order.getProductOrderID());
			insertStatement.setInt(3, order.getOrderQuantity());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
