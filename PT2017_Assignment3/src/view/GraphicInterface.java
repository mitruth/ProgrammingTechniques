package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import bll.Bill;
import bll.ClientBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Product;
import bll.ReflectionExample;
import model.Client;
import model.Order;

public class GraphicInterface {
	
	protected static final Logger LOGGER = Logger.getLogger(GraphicInterface.class.getName());

	private JFrame frame;
	
	private JTabbedPane application;
	
	private JPanel product, client, order;
	
	private JScrollPane products, clients, orders;
	private JScrollPane productList, clientList;
	
	private JTable productTable, clientTable, orderTable;
	
	private JLabel lblProductID, lblQuantity, lblProductName, lblProductStock;
	private JLabel lblClientID, lblClientName;
	private JLabel lblClientOrderID, lblProductOrderID, lblClientList, lblProductList, lblOrderQuantity;
	
	private JTextField txtProductID, txtProductQuantity, txtProductStock, txtProductName;
	private JTextField txtClientID, txtClientName;
	private JTextField txtClientOrderID, txtProductOrderID, txtOrderQuantity;
	
	private JTextArea textListOfProducts, textListOfClients;
	
	private JButton btnShowProducts, btnInsertProduct, btnDeleteProduct, btnUpdateProduct;
	private JButton btnShowClients, btnInsertClients, btnDeleteClients, btnUpdateClients;
	private JButton btnNewOrder;
	
	String productListCreator = new String("");
	String clientListCreator = new String("");
	public GraphicInterface() {
		
		frame = new JFrame("Order Management");
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application = new JTabbedPane();
		
		product = new JPanel();
		product.setLayout(null);
		client = new JPanel();
		client.setLayout(null);
		order = new JPanel();
		order.setLayout(null);
			
		//////////// PRODUCT SECTION /////////////
		lblProductID = new JLabel("Product ID:");
		lblProductID.setBounds(15, 15, 70, 20);
		product.add(lblProductID);
		
		txtProductID = new JTextField();
		txtProductID.setBounds(85, 15, 100, 20);
		product.add(txtProductID);
		
		lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(210, 15, 100, 20);
		product.add(lblProductName);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(310, 15, 100, 20);
		product.add(txtProductName);
		
		lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(435, 15, 60, 20);
		product.add(lblQuantity);
		
		txtProductQuantity = new JTextField();
		txtProductQuantity.setBounds(495, 15, 100, 20);
		product.add(txtProductQuantity);
		
		lblProductStock = new JLabel("Available stock:");
		lblProductStock.setBounds(615, 15, 100, 20);
		product.add(lblProductStock);
		
		txtProductStock = new JTextField();
		txtProductStock.setBounds(715, 15, 100, 20);
		product.add(txtProductStock);
		
		products = new JScrollPane(productTable);
		products.setBounds(15, 370, 852, 350);
		products.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		product.add(products);
		
		btnShowProducts = new JButton("SHOW ALL PRODUCTS");
		btnShowProducts.setBounds(350, 100, 200, 50);
		btnShowProducts.setForeground(Color.BLUE);
		
		btnShowProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Product> productList = new ArrayList<Product>();
				ProductBLL productBll = new ProductBLL();
				productList = productBll.findAll();
				List<Object> objects = new ArrayList<Object>(productList);
				productTable = ReflectionExample.createT(objects);
				products.setViewportView(productTable);
			}
		});
		
		product.add(btnShowProducts);
		
		btnInsertProduct = new JButton("INSERT PRODUCT");
		btnInsertProduct.setBounds(350, 170, 200, 50);
		btnInsertProduct.setForeground(Color.GRAY);
		
		btnInsertProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtProductName.getText().length() != 0 && txtProductQuantity.getText().length() != 0 && txtProductStock.getText().length() != 0 ) {
					String name = txtProductName.getText();
					int quantity = (int)Integer.parseInt(txtProductQuantity.getText());
					int stock = (int)Integer.parseInt(txtProductStock.getText());
					ProductBLL productBll = new ProductBLL();
					Product p = new Product(quantity, name, stock);
					p.setProductID(productBll.insertProduct(p));
				} else {
					JOptionPane.showMessageDialog(null,"WRONG INPUT DATA FOR INSERTION");
				}
			}
		});
		
		product.add(btnInsertProduct);
		
		btnDeleteProduct = new JButton("DELETE PRODUCT");
		btnDeleteProduct.setBounds(350, 240, 200, 50);
		btnDeleteProduct.setForeground(Color.RED);
		
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtProductID.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtProductID.getText());
					ProductBLL productBll = new ProductBLL();
					productBll.deleteProduct(id);
				} else {
					JOptionPane.showMessageDialog(null,"WRONG INPUT DATA FOR DELETION");
				}
			}
		});
		
		product.add(btnDeleteProduct);
		
		btnUpdateProduct = new JButton("UPDATE PRODUCT");
		btnUpdateProduct.setBounds(350, 310, 200, 50);
		btnUpdateProduct.setForeground(Color.GREEN);
		
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtProductID.getText().length() != 0 && txtProductName.getText().length() != 0 && txtProductQuantity.getText().length() != 0 && txtProductStock.getText().length() != 0 ) {
					int id = (int)Integer.parseInt(txtProductID.getText());
					String name = txtProductName.getText();
					int quantity = (int)Integer.parseInt(txtProductQuantity.getText());
					int stock = (int)Integer.parseInt(txtProductStock.getText());
					ProductBLL productBll = new ProductBLL();
					productBll.updateProduct(quantity, name, stock, id);
				} else {
					JOptionPane.showMessageDialog(null,"WRONG INPUT DATA FOR INSERTION");
				}
			}
		});
		
		product.add(btnUpdateProduct);
		
		//////////////////////////// CLIENT SECTION ////////////////////////
		lblClientID = new JLabel("Client ID:");
		lblClientID.setBounds(15, 15, 60, 20);
		client.add(lblClientID);
		
		txtClientID = new JTextField();
		txtClientID.setBounds(75, 15, 150, 20);
		client.add(txtClientID);
		
		lblClientName = new JLabel("Client Name:");
		lblClientName.setBounds(400, 15, 85, 20);
		client.add(lblClientName);
		
		txtClientName = new JTextField();
		txtClientName.setBounds(485, 15, 150, 20);
		client.add(txtClientName);
		
		btnShowClients = new JButton("SHOW ALL CLIENTS");
		btnShowClients.setBounds(350, 100, 200, 50);
		btnShowClients.setForeground(Color.BLUE);
		
		clients = new JScrollPane(clientTable);
		clients.setBounds(15, 370, 852, 350);
		clients.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		client.add(clients);
		
		btnShowClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Client> clientList = new ArrayList<Client>();
				ClientBLL clientBll = new ClientBLL();
				clientList = clientBll.findAll();
				List<Object> objects = new ArrayList<Object>(clientList);
				clientTable = ReflectionExample.createT(objects);
				clients.setViewportView(clientTable);
				
			}
		});
		
		client.add(btnShowClients);
		
		btnInsertClients = new JButton("INSERT CLIENT");
		btnInsertClients.setBounds(350, 170, 200, 50);
		btnInsertClients.setForeground(Color.GRAY);
		
		btnInsertClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtClientName.getText().length() != 0) {
					String name = txtClientName.getText();
					
					ClientBLL clientBll = new ClientBLL();
					Client c = new Client(name);
					c.setClientID(clientBll.insertClient(c));
					clientListCreator += c.toString();
				} else {
					JOptionPane.showMessageDialog(null,"WRONG INPUT DATA FOR INSERTION");
				}
			}
		});
		
		client.add(btnInsertClients);
		
		btnDeleteClients = new JButton("DELETE CLIENT");
		btnDeleteClients.setBounds(350, 240, 200, 50);
		btnDeleteClients.setForeground(Color.RED);
		
		btnDeleteClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtClientID.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtClientID.getText());
					ClientBLL clientBll = new ClientBLL();
					clientBll.deleteClient(id);
				} else {
					JOptionPane.showMessageDialog(null,"WRONG INPUT DATA FOR DELETION");
				}
			}
		});
		
		client.add(btnDeleteClients);
		
		btnUpdateClients = new JButton("UPDATE CLIENT");
		btnUpdateClients.setBounds(350, 310, 200, 50);
		btnUpdateClients.setForeground(Color.GREEN);
		
		btnUpdateClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtClientID.getText().length() != 0 && txtClientName.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtClientID.getText());
					String name = txtClientName.getText();
					ClientBLL clientBll = new ClientBLL();
					clientBll.updateClient(name, id);
				} else {
					JOptionPane.showMessageDialog(null,"WRONG INPUT DATA FOR INSERTION");
				}
			}
		});
		
		client.add(btnUpdateClients);
		
		////////////////////////// ORDER SECTION ////////////////////
		lblClientOrderID = new JLabel("Choose a client ID from the list below:");
		lblClientOrderID.setBounds(15, 15, 250, 20);
		order.add(lblClientOrderID);
		
		txtClientOrderID = new JTextField();
		txtClientOrderID.setBounds(265, 15, 80, 20);
		order.add(txtClientOrderID);
		
		lblProductOrderID = new JLabel("Choose a product ID from the list below:");
		lblProductOrderID.setBounds(365, 15, 260, 20);
		order.add(lblProductOrderID);
		
		txtProductOrderID = new JTextField();
		txtProductOrderID.setBounds(625, 15, 80, 20);
		order.add(txtProductOrderID);
		
		orders = new JScrollPane(orderTable);
		orders.setBounds(15, 370, 852, 350);
		orders.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		order.add(orders);
		
		lblClientList = new JLabel("Client List");
		lblClientList.setBounds(15, 110, 400, 20);
		order.add(lblClientList);
		
		textListOfClients = new JTextArea();
		textListOfClients.setEditable(false);
		textListOfClients.setBounds(15, 130, 400, 220);
		textListOfClients.setLineWrap(true);
		textListOfClients.setWrapStyleWord(true);
		
		List<Client> clientL = new ArrayList<Client>();
		ClientBLL clientBll = new ClientBLL();
		clientL = clientBll.findAll();
		for (int i = 0; i < clientL.size(); i++) {
			clientListCreator += clientL.get(i).toString();
		}
		textListOfClients.setText(clientListCreator);
		
		clientList = new JScrollPane(textListOfClients);
		clientList.setBounds(15, 130, 400, 220);
		clientList.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		order.add(clientList);
		
		lblProductList = new JLabel("Product List");
		lblProductList.setBounds(465, 110, 400, 20);
		order.add(lblProductList);
		
		textListOfProducts = new JTextArea();
		textListOfProducts.setEditable(false);
		textListOfProducts.setBounds(465, 130, 400, 220);
		textListOfProducts.setLineWrap(true);
		textListOfProducts.setWrapStyleWord(true);
		order.add(textListOfProducts);
		
		List<Product> productL = new ArrayList<Product>();
		ProductBLL productBll = new ProductBLL();
		productL = productBll.findAll();
		for (int i = 0; i < productL.size(); i++) {
			productListCreator += productL.get(i).toString();
		}
		textListOfProducts.setText(productListCreator);
		
		productList = new JScrollPane(textListOfProducts);
		productList.setBounds(465, 130, 400, 220);
		productList.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		order.add(productList);
		
		lblOrderQuantity = new JLabel("Quantity:");
		lblOrderQuantity.setBounds(200, 65, 60, 20);
		order.add(lblOrderQuantity);
		
		txtOrderQuantity = new JTextField();
		txtOrderQuantity.setBounds(265, 65, 80, 20);
		order.add(txtOrderQuantity);
		
		btnNewOrder = new JButton("INSERT ORDER");
		btnNewOrder.setBounds(465, 50, 200, 50);
		btnNewOrder.setForeground(Color.BLACK);
		
		List<Order> orderList = new ArrayList<Order>();
		
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtClientOrderID.getText().length() != 0 && txtProductOrderID.getText().length() != 0 && txtOrderQuantity.getText().length() != 0) {
					int clientID = (int)Integer.parseInt(txtClientOrderID.getText());
					int productID = (int)Integer.parseInt(txtProductOrderID.getText());
					int quantity = (int)Integer.parseInt(txtOrderQuantity.getText());
					Product p = ProductDAO.findById(productID);
					Client c = ClientDAO.findById(clientID);

					if ((p.getStock() - quantity) < 0) {
						JOptionPane.showMessageDialog(null,"NOT ENOUGH ITEMS IN STOCK FOR THIS COMMAND. AVAILABLE STOCK IS: " + p.getStock());
					} else {
						ProductBLL productBll = new ProductBLL();
						Bill bill = new Bill();
						int s = p.getStock();
						p.setStock(s - quantity);
						productBll.updateProduct(quantity, p.getProductName(), p.getStock(), productID);
						bill.generateBill(p, c, quantity);
						Order o = new Order(clientID, productID, quantity);
						orderList.add(o);
						List<Object> objects = new ArrayList<Object>(orderList);
						orderTable = ReflectionExample.createT(objects);
						orders.setViewportView(orderTable);
					}
				} else {
					JOptionPane.showMessageDialog(null,"WRONG DATA FOR A NEW ORDER");
				}
			}
		});
		
		order.add(btnNewOrder);
		
		application.add("ProductPanel", product);
		application.add("ClientPanel", client);
		application.add("OrderPanel", order);
		frame.add(application);
		frame.setVisible(true);
	}
}
