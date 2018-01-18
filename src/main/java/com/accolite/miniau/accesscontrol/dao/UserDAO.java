package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;

public interface UserDAO {

	public void setDataSource(DataSource dataSource);

	public boolean addNewUser(User user);

	public User getUser(int userId);

	public boolean addPermissionToUser(int userId, int permissionId);

	public boolean removePermissionFromUser(int userId, int permissionId);

	public boolean deleteUser(int userId);

	public boolean updatePassword(int adminId, String password);

	public List<User> getAllUsers();

	public List<String> getAllUserNames();

	public List<Permission> getPermissionOfUser(int userId);
}
