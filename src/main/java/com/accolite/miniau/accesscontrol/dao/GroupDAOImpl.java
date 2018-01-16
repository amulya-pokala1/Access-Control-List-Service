
package com.accolite.miniau.accesscontrol.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.PermissionType;
import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;

public class GroupDAOImpl implements GroupDAO {
	private JdbcTemplate jdbcTemplate;
	static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.dao.UserDAOImpl.class);

	public GroupDAOImpl() {
		BasicConfigurator.configure();
	}

	public boolean addNewGroup(Group group) {

		String query = "INSERT INTO ACL.GROUP(GROUPID, GROUPNAME, PERMISSIONTYPE) VALUES(?,?,?)";
		// TODO CHANGE THE QUERY
		int rowsAffected = jdbcTemplate.update(query, group.getGroupId(), group.getGroupName(),
				group.getPermissionType().name());
		if (rowsAffected == 0) {
			logger.error("couldn't insert a new group into the table group");
			return false;
		}
		logger.info("inserted a new group into the table group successfully");
		return true;

	}

	public boolean updatePermission(int groupId, PermissionType permissionType) {

		String query = "UPDATE ACL.GROUP SET PERMISSIONTYPE=? WHERE GROUPID=?";

		int rowsAffected = jdbcTemplate.update(query, permissionType.name(), groupId);
		if (rowsAffected == 0) {
			logger.error("failed to update permission of group");
			return false;
		}
		logger.info("updated permission of group successfully");
		return true;
	}

	public List<String> getAllGroupNames() {
		String query = "SELECT GROUPNAME FROM ACL.GROUP";
		logger.info("performing get all group names operation");
		return jdbcTemplate.queryForList(query, String.class);

	}

	public List<Group> getAllGroups() {
		String query = "SELECT * FROM ACL.GROUP";
		logger.info("performing get all groups operation");
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<Group>(Group.class));

	}

	public List<User> getUsersInGroup(int groupId) {
		String query = "SELECT USERID FROM ACL.USER_GROUP WHERE GROUPID=?";
		logger.info("performing get all users in group operation");
		List<Integer> ids = jdbcTemplate.queryForList(query, new Object[] { groupId }, Integer.class);
		List<User> users = new ArrayList<>();
		for (int id : ids) {
			String query1 = "SELECT * FROM USER WHERE USERID=?";

			User u = jdbcTemplate.queryForObject(query1, new Object[] { id },
					new BeanPropertyRowMapper<User>(User.class));
			users.add(u);

		}
		logger.info("operation get users in group successfull");
		return users;

	}

	public boolean addUserToGroup(int groupId, User user) {
		String query = "INSERT INTO ACL.USER_GROUP(GROUPID, USERID) VALUES(?,?)";
		int rowsAffected = jdbcTemplate.update(query, groupId, user.getUserId());
		if (rowsAffected == 0) {
			logger.info("failed to add user in group");
			return false;
		}
		logger.info("successfully added user to group");
		return true;
	}

	public boolean removeUserFromGroup(int groupId, int userId) {
		String query = "DELETE FROM USER_GROUP WHERE USERID=? AND GROUPID=?";
		int rowsAffected = jdbcTemplate.update(query, userId, groupId);
		if (rowsAffected == 0) {
			logger.info("failed to remove user from group");
			return false;
		}
		logger.info("successfully deleted user from group");
		return true;
	}

	public boolean deleteGroup(int groupId) {
		String query = "DELETE FROM ACL.GROUP WHERE GROUPID=?";
		String query1 = "DELETE FROM ACL.USER_GROUP WHERE GROUPID=?";
		logger.info("deleting the group from the tables group, user_group");
		int rowsAffected = jdbcTemplate.update(query, groupId);
		jdbcTemplate.update(query1, groupId);
		if (rowsAffected == 0) {
			logger.info("failed to delete group");
			return false;
		}
		logger.info("deleted group successfully");
		return true;
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("setting the data source has been successful");

	}

	@Override
	public boolean addPermission(int groupId, Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePermission(int groupId, Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
