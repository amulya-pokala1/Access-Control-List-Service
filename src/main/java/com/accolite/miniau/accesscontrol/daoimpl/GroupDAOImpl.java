
package com.accolite.miniau.accesscontrol.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.miniau.accesscontrol.dao.GroupDAO;
import com.accolite.miniau.accesscontrol.mapper.GroupMapper;
import com.accolite.miniau.accesscontrol.mapper.UserMapper;
import com.accolite.miniau.accesscontrol.model.Group;
import com.accolite.miniau.accesscontrol.model.User;
import com.accolite.miniau.accesscontrol.utility.Query;

public class GroupDAOImpl implements GroupDAO {
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(com.accolite.miniau.accesscontrol.daoimpl.GroupDAOImpl.class);

	@Override
	public boolean addNewGroup(Group group) {

		int rowsAffected;
		try {
			rowsAffected = jdbcTemplate.update(Query.ADDNEWGROUP, group.getGroupName(), group.getGroupDescription());
			int groupId = jdbcTemplate.queryForObject(Query.GETGROUPID, new Object[] { group.getGroupName() },
					Integer.class);
			group.setGroupId(groupId);

		} catch (Exception e) {
			rowsAffected = 0;
		}
		if (rowsAffected == 0) {
			logger.error("couldn't insert " + group.getGroupName() + " into the table group");
			return false;
		}
		logger.info("inserted " + group.getGroupId() + "into the table group successfully");
		return true;

	}

	@Override
	public List<String> getAllGroupNames() {
		logger.info("performing get all group names operation");
		return jdbcTemplate.queryForList(Query.GETALLGROUPNAMES, String.class);

	}

	@Override
	public List<Group> getAllGroups() {
		logger.info("performing get all groups operation");
		return jdbcTemplate.query(Query.GETALLGROUPS, new GroupMapper());

	}

	@Override
	public List<User> getUsersInGroup(int groupId) {
		logger.info("operation get users in group successfull");
		return jdbcTemplate.query(Query.GETUSERSINGROUP, new Object[] { groupId }, new UserMapper());

	}

	@Override
	public boolean addUserToGroup(int groupId, int userId) {
		int rowsAffected = -1;
		try {
			rowsAffected = jdbcTemplate.update(Query.ADDUSERTOGROUP, groupId, userId);

		} catch (DataAccessException e) {
			rowsAffected = 0;
		}
		if (rowsAffected == 0) {
			logger.info("failed to add " + userId + "user in group");
			return false;
		}
		logger.info("successfully added" + userId + " user to group");
		return true;
	}

	@Override
	public boolean removeUserFromGroup(int groupId, int userId) {
		int rowsAffected = jdbcTemplate.update(Query.REMOVEUSERFROMGROUP, userId, groupId);
		if (rowsAffected == 0) {
			logger.info("failed to remove " + userId + "user from group" + groupId);
			return false;
		}
		logger.info("successfully deleted" + userId + " user from group" + groupId);
		return true;
	}

	@Override
	public boolean deleteGroup(int groupId) {
		String query = "DELETE FROM ACL.GROUP WHERE GROUP_ID=?";
		logger.info("deleting the group from the tables group, user_group");
		int rowsAffected = jdbcTemplate.update(query, groupId);
		if (rowsAffected == 0) {
			logger.info("failed to delete group" + groupId);
			return false;
		}
		logger.info("deleted group" + groupId + " successfully");
		return true;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public boolean addPermission(int groupId, int permissionId) {
		int rowsAffected;
		try {
			rowsAffected = jdbcTemplate.update(Query.ADDPERMISSION, groupId, permissionId);
		} catch (DataAccessException e) {
			rowsAffected = 0;
		}
		if (rowsAffected == 0) {
			logger.info("failed to add permission " + permissionId + " to group" + groupId);
			return false;
		}
		logger.info("successfully added permission" + permissionId + " to group" + groupId);
		return true;
	}

	@Override
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
