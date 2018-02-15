package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Bank;
import model.Person;

public class GUI {

	private JFrame frame;
	
	private JTabbedPane application;
	
	private JPanel person, account;
	
	private JScrollPane persons, accounts;
	
	private JTable personTable, accountTable;
	
	private JLabel lblPersonID, lblPersonName;
	private JLabel lblAccountID, lblPerson, lblCash, lblType, lblPerID;
	
	private JTextField txtPersonID, txtPersonName;
	private JTextField txtAccountID, txtPerson, txtCash, txtType, txtPerID;
	
	private JButton btnShowPersons, btnAddPerson, btnEditPerson, btnDeletePerson;
	private JButton btnShowAccounts, btnAddAccount, btnDeleteAccount, btnWithdraw, btnDeposit;
	
	private Bank b;
	
	public GUI() {
		frame = new JFrame("Bank");
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application = new JTabbedPane();
		
		person = new JPanel();
		person.setLayout(null);
		account = new JPanel();
		account.setLayout(null);
		
		b = new Bank();
		b = b.writeAccountData();
		
		/////////////////// PERSON SECTION //////////////////////
		lblPersonID = new JLabel("Person id: ");
		lblPersonID.setBounds(15, 15, 70, 20);
		person.add(lblPersonID);
		
		txtPersonID = new JTextField();
		txtPersonID.setBounds(85,  15,  100, 20);
		person.add(txtPersonID);
		
		lblPersonName = new JLabel("Person name: ");
		lblPersonName.setBounds(210, 15, 100, 20);
		person.add(lblPersonName);
		
		txtPersonName = new JTextField();
		txtPersonName.setBounds(310, 15, 100, 20);
		person.add(txtPersonName);
		
		btnShowPersons = new JButton("SHOW PERSONS");
		btnShowPersons.setBounds(350, 100, 250, 50);
		btnShowPersons.setForeground(Color.BLUE);
		
		persons = new JScrollPane(personTable);
		persons.setBounds(15, 370, 852, 350);
		persons.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		person.add(persons);
		
		btnShowPersons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b = b.writeAccountData();
				ArrayList <Person> personList = new ArrayList<Person>();
				personList = b.getPersons();
				if (personList.size() != 0) {
					String[] cols = new String[2];
					cols[0] = "ID";
					cols[1] = "PersonName";
					
					DefaultTableModel table = new DefaultTableModel(cols, 0);
					
					for (int i = 0; i < personList.size(); i++) {
						Object[] row = new Object[2];
						row[0] = personList.get(i).getID();
						row[1] = personList.get(i).getName();
						table.addRow(row);
					}
					JTable t = new JTable(table);
					persons.setViewportView(t);
				} else {
					JOptionPane.showMessageDialog(null, "NO PERSONS IN THE BANK TO SHOW");
				}
			}
		});
		
		person.add(btnShowPersons);
		
		btnAddPerson = new JButton("ADD PERSON");
		btnAddPerson.setBounds(350, 170, 250, 50);
		btnAddPerson.setForeground(Color.GRAY); 
		
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPersonID.getText().length() != 0 && txtPersonName.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtPersonID.getText());
					String name = txtPersonName.getText();
					Person p = new Person(id, name);
					b.addPerson(p);
					b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR ENTERING A PERSON");
				}
			}
		});
		
		person.add(btnAddPerson);
		
		btnDeletePerson = new JButton("DELETE PERSON");
		btnDeletePerson.setBounds(350, 240, 250, 50);
		btnDeletePerson.setForeground(Color.RED);
		
		btnDeletePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPersonID.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtPersonID.getText());
					b.removePerson(id);
					b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR DELETING A PERSON");
				}
			}
		});
		
		person.add(btnDeletePerson);
		
		btnEditPerson = new JButton("EDIT PERSON");
		btnEditPerson.setBounds(350,  310, 250, 50);
		btnEditPerson.setForeground(Color.GREEN);
		
		btnEditPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPersonID.getText().length() != 0 && txtPersonName.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtPersonID.getText());
					String name = txtPersonName.getText();
					b.editPerson(id, name);
					b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR EDITING A PERSON");

				}
			}
		});
		
		person.add(btnEditPerson);
		
		/////////////////// ACCOUNT SECTION //////////////////////
		lblAccountID = new JLabel("Account id: ");
		lblAccountID.setBounds(15, 15, 90, 20);
		account.add(lblAccountID);
		
		txtAccountID = new JTextField();
		txtAccountID.setBounds(85, 15, 100, 20);
		account.add(txtAccountID);
		
		
		lblPerson = new JLabel("Person: ");
		lblPerson.setBounds(260, 15, 50, 20);
		account.add(lblPerson);
		
		txtPerson = new JTextField();
		txtPerson.setBounds(310, 15, 100, 20);
		account.add(txtPerson);
		
		btnShowAccounts = new JButton("SHOW ALL ACCOUNTS");
		btnShowAccounts.setBounds(15, 100, 250, 50);
		btnShowAccounts.setForeground(Color.BLUE);
		
		accounts = new JScrollPane(accountTable);
		accounts.setBounds(15, 370, 852, 350);
		accounts.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		account.add(accounts);
		
		btnShowAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b = b.writeAccountData();
				ArrayList <Account> accountList = new ArrayList<Account>();
				accountList = b.getAccounts();
				if (accountList.size() != 0) {
					String[] cols = new String[3];
					cols[0] = "ID";
					cols[1] = "Person";
					cols[2] = "Cash";
					
					DefaultTableModel table = new DefaultTableModel(cols, 0);
					
					for (int i = 0; i < accountList.size(); i++) {
						Object[] row = new Object[4];
						row[0] = accountList.get(i).getAccountID();
						row[1] = accountList.get(i).getPerson().getName();
						row[2] = accountList.get(i).getDeposit();
						table.addRow(row);
					}
					JTable t = new JTable(table);
					accounts.setViewportView(t);
				} else {
					JOptionPane.showMessageDialog(null, "NO ACCOUNTS IN THE BANK TO SHOW");
				}
			}
		});
		
		account.add(btnShowAccounts);
		
		lblType = new JLabel("Account type: ");
		lblType.setBounds(270, 170, 100, 20);
		account.add(lblType);
		
		txtType = new JTextField();
		txtType.setBounds(370, 170, 100, 20);
		account.add(txtType);
		
		lblPerID = new JLabel("Person id: ");
		lblPerID.setBounds(270, 190, 100, 20);
		account.add(lblPerID);
		
		txtPerID = new JTextField();
		txtPerID.setBounds(370, 190, 100, 20);
		account.add(txtPerID);
		
		btnAddAccount = new JButton("ADD ACCOUNT");
		btnAddAccount.setBounds(15, 170, 250, 50);
		btnAddAccount.setForeground(Color.GRAY);
		
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAccountID.getText().length() != 0 && txtType.getText().length() != 0 
						&& txtPerID.getText().length() != 0) {
							int accID = (int)Integer.parseInt(txtAccountID.getText());
							int accType = (int)Integer.parseInt(txtType.getText());
							int perID = (int)Integer.parseInt(txtPerID.getText());
							b.addAccount(perID, accID, accType);
							b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR ENTERING AN ACCOUNT");

				}
			}
		});
		
		account.add(btnAddAccount);
		
		btnDeleteAccount = new JButton("DELETE ACCOUNT");
		btnDeleteAccount.setBounds(15, 240, 250, 50);
		btnDeleteAccount.setForeground(Color.RED);
		
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAccountID.getText().length() != 0 || b.findAccountByID((int)Integer.parseInt(txtAccountID.getText())) != null) {
					int id = (int)Integer.parseInt(txtAccountID.getText());
					b.removeAccount(id);
					b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR DELETING AN ACCOUNT");
				}
			}
		});
		
		account.add(btnDeleteAccount);
		
		lblCash = new JLabel("Cash: ");
		lblCash.setBounds(600, 100, 40, 20);
		account.add(lblCash);
		
		txtCash = new JTextField();
		txtCash.setBounds(640, 100, 100, 20);
		account.add(txtCash);
		
		btnWithdraw = new JButton("WITHDRAW");
		btnWithdraw.setBounds(600, 170, 250, 50);
		btnWithdraw.setForeground(Color.BLACK);
		
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAccountID.getText().length() != 0 && txtCash.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtAccountID.getText());
					double cash = (double)Double.parseDouble(txtCash.getText());
					b.WithdrawFromAccount(id, cash);
					b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR WITHDRAWING FROM AN ACCOUNT");
				}
			}
		});
		
		account.add(btnWithdraw);
		
		btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setBounds(600, 240, 250, 50);
		btnDeposit.setForeground(Color.BLACK);
		
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAccountID.getText().length() != 0 && txtCash.getText().length() != 0) {
					int id = (int)Integer.parseInt(txtAccountID.getText());
					double cash = (double)Double.parseDouble(txtCash.getText());
					b.depositIntoAccount(id, cash);
					b.readAccountData(b);
				} else {
					JOptionPane.showMessageDialog(null, "WRONG INPUT DATA FOR DEPOSITING INTO AN ACCOUNT");
				}
			}
		});
		
		account.add(btnDeposit);
		
		application.add("PersonPanel", person);
		application.add("AccountPanel", account);
		frame.add(application);
		frame.setVisible(true);
	}
}
