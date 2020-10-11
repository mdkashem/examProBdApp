package examPro.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import examPro.com.model.Account;
import examPro.com.utilities.DAOUtilities;
public class AccountDAOImpl implements AccountDAO{
	

	Connection connection = null;
	PreparedStatement stmt = null;

	public boolean addAccount(Account account) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO account(accountid, balance, created_on, statusid, typeid) VALUES(default, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setDouble(1, account.getBalance());
			stmt.setDate(2, account.getCreated_on());
			stmt.setInt(3, account.getStatusId());
			stmt.setInt(4, account.getTypeId());
			
			if(stmt.executeUpdate()!=0) {
				return true;
			}else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			
			closeResources();
		}
	}

	public List<Account> getAllAccount() {
		List <Account> accountList = new ArrayList<Account>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM account";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Account account = new Account(rs.getInt("accountid"), rs.getDouble("balance"), rs.getDate("created_on"), rs.getInt("statusid"), rs.getInt("typeid"));
				accountList.add(account);
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		
		return accountList;
	}

	public Account getAccountById(int id) {
		Account acc = null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Account WHERE accountid = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				acc = new Account();
				acc.setAccountId(rs.getInt("accountid"));
				acc.setBalance(rs.getDouble("balance"));
				acc.setCreated_on(rs.getDate("created_on"));
				acc.setStatusId(rs.getInt("statusid"));
				acc.setTypeId(rs.getInt("typeid"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return acc;
	}

	public boolean updateAccount(Account account) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE account SET balance=?, created_on=?, statusid=?, typeid=? WHERE accountid=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setDouble(1, account.getBalance());
			stmt.setDate(2, account.getCreated_on());
			stmt.setInt(3, account.getStatusId());
			stmt.setInt(4, account.getTypeId());
			stmt.setInt(5, account.getAccountId());
			
			
			//System.out.println(stmt);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	public boolean updateBalance(int accountId, double newBalance) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE account SET balance=? WHERE accountid=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setDouble(1, newBalance);
			stmt.setInt(2, accountId);
			
			
			
			//System.out.println(stmt);
			
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	public boolean deleteAccountByID(int id) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM account WHERE accountid = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
	}
	
	// Closing all resources to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

	public int findLastAccountId(Account account) {
		int ID = -1;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO account(accountid, balance, created_on, statusid, typeid) VALUES(default, ?, ?, ?, ?) RETURNING accountid";
			//String sql = "INSERT INTO account(accountid, balance, statusid, typeid) VALUES(default, ?, ?, ?) RETURNING accountid";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			
			stmt.setDouble(1, account.getBalance());
			stmt.setDate(2, account.getCreated_on());
			stmt.setInt(3, account.getStatusId());
			stmt.setInt(4, account.getTypeId());
			
			//stmt.setDouble(1, account.getBalance());
			//stmt.setInt(2, account.getStatusId());
			//stmt.setInt(3, account.getTypeId());
			
			
	
			int update = stmt.executeUpdate();
					 ResultSet rs = stmt.getGeneratedKeys();
					 if (rs != null && rs.next()) {
					  ID = rs.getInt(1);
					  return ID;
					 }else {
						 return ID;
					 }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ID;
			
		}finally {
			
			closeResources();
		}
		
	}

	

}
