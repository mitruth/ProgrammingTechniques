package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	//A Logger object is used to log messages for a specific system or application component
	private static final String insertStatementString = "INSERT INTO Client (ID, name)"
			+ " VALUES (?,?)";
	private final static String findIDStatementString = "SELECT * FROM Client where ID = ?";
	
	private static final String findAllStatementString = "Select * FROM Client";
	
	private static final String deleteStatementString = "DELETE FROM Client WHERE ID = ?";
	
	private static final String updateStatementString = "UPDATE Client SET name = ? WHERE ID = ?";
	
	public static List<Client> findAll() {
		List<Client> allClients = new ArrayList<Client>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;
		
		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				allClients.add(new Client(id, name));
			}
		}catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findAll " + e.getMessage());
		}finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return allClients;
	}
	
	public static Client findById(int clientId) {
		Client toReturn = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		
		try{
			findStatement = dbConnection.prepareStatement(findIDStatementString);
			findStatement.setLong(1, clientId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			toReturn = new Client(clientId, name);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		}finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, client.getID());
			insertStatement.setString(2, client.getName());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void delete(int clientId) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			
			deleteStatement.setInt(1, clientId);
			deleteStatement.executeUpdate();

		}catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:delete " + e.getMessage());
		}finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void update(String name, int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;

		try{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, name);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
}
