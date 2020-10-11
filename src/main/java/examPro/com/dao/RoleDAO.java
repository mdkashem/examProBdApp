package examPro.com.dao;
import examPro.com.model.Role;

import java.util.List;



public interface RoleDAO {
	public boolean addRole(Role role);
	public List<Role> getAllRole();
	public Role getRoleByName(String name);
	public Role getRoleById(int id);
	
	boolean updateRole(Role role);
	public boolean deleteRoleByID(int id);
	
}
