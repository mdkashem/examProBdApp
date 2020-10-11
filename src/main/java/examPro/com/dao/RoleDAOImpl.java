package examPro.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import examPro.com.model.Role;
import examPro.com.utilities.DAOUtilities;


public class RoleDAOImpl implements RoleDAO{
	Connection connection = null;
	PreparedStatement stmt = null;	
	
	public boolean addRole(Role role) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO role(roleid, role) VALUES(default, ?)";
			stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setString(1, role.getRole());
			
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
	
	public List<Role> getAllRole() {
		List <Role> roleList = new ArrayList<Role>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM role ";
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Role role = new Role(rs.getInt("roleid"), rs.getString("role"));
				roleList.add(role);
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources();
		}
		
		
		return roleList;
	}

	public Role getRoleByName(String name) {
		Role role=null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM role WHERE role LIKE ?";
			stmt = connection.prepareStatement(sql);
			// This command populate the 1st '?' with the title and wildcards, between ' '
			stmt.setString(1, "%" + name + "%");
			ResultSet rs =stmt.executeQuery();
			
			while(rs.next()){
				role=new Role();
				role.setRoleId(Integer.parseInt(rs.getString("roleid")));
				role.setRole(rs.getString("role"));
			}
				
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}

		return role;
	}

	public Role getRoleById(int id) {
		Role role=null;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM role WHERE roleid = ?";
			stmt = connection.prepareStatement(sql);
			// This command populate the 1st '?' with the title and wildcards, between ' '
			stmt.setInt(1, id);
			ResultSet rs =stmt.executeQuery();
			
			while(rs.next()){
				role=new Role();
				role.setRoleId(Integer.parseInt(rs.getString("roleid")));
				role.setRole(rs.getString("role"));
			}
				
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}

		return role;
	}

	

	public boolean updateRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteRoleByID(int id) {
		// TODO Auto-generated method stub
		return false;
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

}
