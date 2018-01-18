package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.User;

public interface GroupDAO {

	public void setDataSource(DataSource dataSource);

	public boolean addNewGroup(Group group);

	public boolean addPermission(int groupId, int permissionId);

	public boolean removePermission(int groupId, int permissionId);

	public List<String> getAllGroupNames();

	public List<Group> getAllGroups();

	public List<User> getUsersInGroup(int groupId);

	public boolean addUserToGroup(int groupId, int userId);

	public boolean removeUserFromGroup(int groupId, int userId);

	public boolean deleteGroup(int groupId);
}
