package examPro.com.dao;
import examPro.com.model.AccountStatus;

import java.util.List;
public interface StatusDAO {
	    
	
	    public boolean addStatus(AccountStatus status);
	    public List<AccountStatus> getAllStatus();
		public AccountStatus getStatusByName(String name);
		public List<AccountStatus> getStatusById(String id);
		public boolean updateStatus(AccountStatus status);
		public boolean deleteStausByID(int id);
}
