package examPro.com.dao;

import java.util.List;

import examPro.com.model.Account;

public interface AccountDAO {
	
	    public boolean addAccount(Account account);    
	    public List<Account> getAllAccount();
	    public int findLastAccountId(Account account);
		public Account getAccountById(int id);
		public boolean updateAccount(Account account);
		public boolean deleteAccountByID(int id);
		public boolean updateBalance(int accountId, double newBalance);

}
