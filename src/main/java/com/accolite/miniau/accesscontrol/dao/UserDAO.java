package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.PermissionType;
import com.accolite.miniau.accesscontrol.model.User;

public interface UserDAO {

	public void setDataSource(DataSource dataSource);

	public boolean addNewUser(User user);

	public User getUser(int userId);

	public boolean addPermission(int userId, Permission permission);

	public boolean removePermission(int userId, Permission permission);

	public boolean deleteUser(int userId);

	public List<User> getAllUsers();

	public List<String> getAllUserNames();
}
