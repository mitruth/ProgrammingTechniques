package model;

public interface BankProc {

	/*
	 * @param p
	 * 
	 * @pre p.getName() != null
	 * 
	 * @post person count before addition is less with 1 than the current size
	 * after addition
	 */
	public abstract void addPerson(Person p);

	/*
	 * @param personId
	 * 
	 * @pre findById(personId) != null
	 * 
	 * @pre isWellFormed()
	 * 
	 * @post one less person in the table
	 */
	public abstract void removePerson(int personId);

	/*
	 * @param personId, accId, type
	 * 
	 * @pre isWellFormed()
	 * 
	 * @pre person with personId must exist
	 * 
	 * @pre 0 <= type <= 1
	 * 
	 * @post total number of accounts in the bank is bigger than before with 1
	 */
	public abstract void addAccount(int personId, int accID, int type);

	/*
	 * @param accId
	 * 
	 * @pre id must be a valid id from an account in the table
	 * 
	 * @post total number of accounts is less with 1
	 */
	public abstract void removeAccount(int accId);

	/*
	 * @param personId, n
	 * 
	 * @pre the person with the specified id must exist in the table
	 * 
	 * @post the person with the specified id must have the new name : n
	 */
	public abstract void editPerson(int personId, String n);

	/*
	 * @pre a valid file is provided
	 * 
	 * @pre there is at least one person with an associated account in the bank
	 * 
	 * @post
	 */
	public abstract void readAccountData(Bank b);

	/*
	 * @pre isWellFormed()
	 * 
	 * @post isWellFormed()
	 */
	public abstract Bank writeAccountData();

	/*
	 * @pre
	 * 
	 * @post
	 * 
	 * @return if there is a total deposit in the bank greated or equal than 0, returns true, else false
	 */
	public abstract boolean isWellFormed();
}
