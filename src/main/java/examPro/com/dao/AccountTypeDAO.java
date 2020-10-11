package examPro.com.dao;
import examPro.com.model.AccountType;

import java.util.List;


public interface AccountTypeDAO {
	    public boolean addAccountType(AccountType type);    
	    public List<AccountType> getAllAccountType();
		public AccountType getAccountTypeByName(String name);
		public List<AccountType> getAccountTypeById(String id);
		public boolean updateAccountType(AccountType type);
		public boolean deleteAccountTypeByID(int id);

}
