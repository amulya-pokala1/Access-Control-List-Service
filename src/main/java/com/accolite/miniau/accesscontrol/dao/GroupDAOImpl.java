
package com.accolite.miniau.accesscontrol.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.mapper.GroupMapper;
import com.accolite.miniau.accesscontrol.mapper.UserMapper;
import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.Query;

public class GroupDAOImpl implements GroupDAO {
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.dao.GroupDAOImpl.class);

	public GroupDAOImpl() {
		BasicConfigurator.configure();
	}

	public boolean addNewGroup(Group group) {

		int rowsAffected = jdbcTemplate.update(Query.ADDNEWGROUP, group.getGroupId(), group.getGroupName());
		if (rowsAffected == 0) {
			logger.error("couldn't insert " + group.getGroupId() + " into the table group");
			return false;
		}
		logger.info("inserted " + group.getGroupId() + "into the table group successfully");
		return true;

	}

	public List<String> getAllGroupNames() {
		logger.info("performing get all group names operation");
		return jdbcTemplate.queryForList(Query.GETALLGROUPNAMES, String.class);

	}

	public List<Group> getAllGroups() {
		// TODO review
		logger.info("performing get all groups operation");
		return jdbcTemplate.query(Query.GETALLGROUPS, new GroupMapper());

	}

	public List<User> getUsersInGroup(int groupId) {
		logger.info("operation get users in group successfull");
		return jdbcTemplate.query(Query.GETUSERSINGROUP, new Object[] { groupId }, new UserMapper());

	}

	public boolean addUserToGroup(int groupId, User user) {
		int rowsAffected = jdbcTemplate.update(Query.ADDUSERTOGROUP, groupId, user.getUserId());
		if (rowsAffected == 0) {
			logger.info("failed to add " + user.getUserId() + "user in group");
			return false;
		}
		logger.info("successfully added" + user.getUserId() + " user to group");
		return true;
	}

	public boolean removeUserFromGroup(int groupId, int userId) {
		int rowsAffected = jdbcTemplate.update(Query.REMOVEUSERFROMGROUP, userId, groupId);
		if (rowsAffected == 0) {
			logger.info("failed to remove " + userId + "user from group" + groupId);
			return false;
		}
		logger.info("successfully deleted" + userId + " user from group" + groupId);
		return true;
	}

	public boolean deleteGroup(int groupId) {
		// todo on delete cascade
		String query = "DELETE FROM ACL.GROUP WHERE GROUPID=?";
		String query1 = "DELETE FROM ACL.USER_GROUP WHERE GROUPID=?";
		logger.info("deleting the group from the tables group, user_group");
		int rowsAffected = jdbcTemplate.update(query, groupId);
		jdbcTemplate.update(query1, groupId);
		if (rowsAffected == 0) {
			logger.info("failed to delete group" + groupId);
			return false;
		}
		logger.info("deleted group" + groupId + " successfully");
		return true;
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("setting the data source has been successful");

	}

	public boolean addPermission(int groupId, int permissionId) {
		int rowsAffected = jdbcTemplate.update(Query.ADDPERMISSION, groupId, permissionId);
		if (rowsAffected == 0) {
			logger.info("failed to add permission " + permissionId + " to group" + groupId);
			return false;
		}
		logger.info("successfully added permission" + permissionId + " to group" + groupId);
		return true;
	}

	public boolean removePermission(int groupId, int permissionId) {
		int rowsAffected = jdbcTemplate.update(Query.REMOVEPERMISSION, groupId, permissionId);
		if (rowsAffected == 0) {
			logger.info("failed to delete permission" + permissionId + " from group" + groupId);
			return false;
		}
		logger.info("successfully deleted permission " + permissionId + "from group" + groupId);
		return true;
	}

}
