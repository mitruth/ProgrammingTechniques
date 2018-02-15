package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Bank implements BankProc, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<Person, ArrayList<Account>> hash;

	public Bank() {
		hash = new HashMap<Person, ArrayList<Account>>();// one null key and any
															// number of null
															// values
		// fail fast iterator
		// map interface
		// no guarantee that order will remain constant over time
	}

	public boolean isWellFormed() {// A class invariant is simply a property
									// that holds for all instances of a class,
		// always, no matter what other code does.
		double totalDeposit = 0;
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getValue() != null) {
				for (Account a : h.getValue()) {
					totalDeposit = totalDeposit + a.getDeposit();
				}
			}
		}
		if (totalDeposit >= 0) {
			return true;
		} else {
			return false;
		}
	}

	private Person findPersonByID(int id) {
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getKey().getID() == id) {
				return h.getKey();
			}
		}
		return null;
	}

	public Account findAccountByID(int id) {
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getValue() != null) {
				for (Account a : h.getValue()) {
					if (a.getAccountID() == id) {
						return a;
					}
				}
			}
		}
		return null;
	}

	public int getPersonSize() {
		int personCount = 0;
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getKey() != null) {
				personCount++;
			}
		}
		return personCount;
	}

	public int getAccNr() {
		int accNr = 0;
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getValue() != null) {
				for (Account a : h.getValue()) {
					accNr++;
				}
			}
		}
		return accNr;
	}

	@Override
	public void addPerson(Person p) {
		assert (p.getName() != null);
		int personCount = this.getPersonSize();
		Person person = new Person(p.getID(), p.getName());
		hash.put(person, null);
		assert (personCount + 1 == this.getPersonSize());
	}

	public ArrayList<Account> getAccounts() {
		ArrayList<Account> accs = new ArrayList<Account>();
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getValue() != null) {
				for (Account a : h.getValue()) {
					accs.add(a);
				}
			}
		}
		return accs;
	}

	public ArrayList<Person> getPersons() {
		ArrayList<Person> persons = new ArrayList<Person>();
		for (Entry<Person, ArrayList<Account>> h : hash.entrySet()) {
			if (h.getKey() != null) {
				persons.add(h.getKey());
			}
		}
		return persons;
	}

	@Override
	public void readAccountData(Bank b) { // serialize
		// After a serialized object has been written into a file, it can be
		// read from the file and deserialized that is,
		// the type information and bytes that represent the object and its data
		// can be used to recreate the object in memory.
		try {
			assert (isWellFormed());
			FileOutputStream fileOut = new FileOutputStream("bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(b);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in bank.ser" + "\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public Bank getBank() {
		return this.getBank();
	}

	@Override
	public Bank writeAccountData() { // deserialize
		// This method retrieves the next Object out of the stream and
		// deserializes it.
		// The return value is Object, so you will need to cast it to its
		// appropriate data type.
		try {
			FileInputStream fileIn = new FileInputStream("bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Bank b = (Bank) in.readObject();
			in.close();
			fileIn.close();
			return b;
		} catch (IOException e) {
			e.printStackTrace();
			return new Bank();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return new Bank();
		}
	}

	public void depositIntoAccount(int accID, double sum) {
		assert (isWellFormed());
		Account a = findAccountByID(accID);
		assert (a != null);
		a.increaseDeposit(sum);
		assert (isWellFormed());
	}

	public void WithdrawFromAccount(int accID, double sum) {
		assert (isWellFormed());
		Account a = findAccountByID(accID);
		assert (a != null);
		a.withdrawDeposit(sum);
		assert (isWellFormed());
	}

	@Override
	public void removePerson(int id) {
		Person personById = findPersonByID(id);
		assert (isWellFormed());
		assert (personById != null);
		int personCount = this.getPersonSize();
		hash.remove(personById);
		assert (personCount - 1 == this.getPersonSize());
		assert (isWellFormed());
	}

	@Override
	public void addAccount(int personId, int accID, int type) {
		assert (isWellFormed());
		Person p = findPersonByID(personId);
		assert (type >= 0 && type <= 1);
		int accNumber = this.getAccNr();
		if (type == 0) { // type = 0 => saving account
			Account newAcc = new SavingsAccount(accID, p, 0);
			if (hash.get(p) != null) {
				hash.get(p).add(newAcc);
			} else {
				hash.put(p, new ArrayList<Account>());
				hash.get(p).add(newAcc);
			}
		} else {
			Account newAcc = new SpendingAccount(accID, p, 0);
			if (hash.get(p) != null) {
				hash.get(p).add(newAcc);
			} else {
				hash.put(p, new ArrayList<Account>());
				hash.get(p).add(newAcc);
			}
		}
		assert (accNumber + 1 == this.getAccNr());
		assert (isWellFormed());
	}

	@Override
	public void removeAccount(int id) {
		assert (isWellFormed());
		int size = hash.size();
		Account a = findAccountByID(id);
		assert (a.getAccountID() != 0);
		hash.remove(a.getPerson());
		assert (hash.size() == size - 1);
		assert (isWellFormed());
	}

	@Override
	public void editPerson(int id, String n) {
		assert (isWellFormed());
		Person p = findPersonByID(id);
		assert (p != null);
		p.setName(n);
		assert (p.getName() == n);
		assert (isWellFormed());
	}

	@Override
	public String toString() {
		return "Bank [hashTable = " + hash + "]";
	}

}
