package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {
	
	public Client findClientById(int id) {
		Client c = ClientDAO.findById(id);
		if (c == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return c;
	}
	
	public int insertClient(Client c) {
		return ClientDAO.insert(c);
	}
	
	public List<Client> findAll() {
		List<Client> clients = ClientDAO.findAll();
		return clients;
	}
	
	public void deleteClient(int id) {
		Client c = ClientDAO.findById(id);
		if (c == null) {
			throw new NoSuchElementException("The client with id = " + id + " can not be deleted because it is not in the database!");
		}
		ClientDAO.delete(id);
	}
	
	public void updateClient(String name, int id) {
		ClientDAO.update(name, id);
	}
}
