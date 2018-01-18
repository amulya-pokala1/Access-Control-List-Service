package com.accolite.miniau.accesscontrol.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.accolite.miniau.accesscontrol.model.Group;

public class GroupMapper implements RowMapper<Group> {

	private static Logger logger = Logger.getLogger(GroupMapper.class);

	public Group mapRow(ResultSet rs, int rowNum) {
		Group group = new Group();
		try {
			group.setGroupId(rs.getInt("GROUP_ID"));
			group.setGroupName(rs.getString("GROUP_NAME"));
			group.setGroupDescription(rs.getString("GROUP_DESCRIPTION"));
		} catch (SQLException e) {
			logger.error("Error in parsing Group", e);
		}
		return group;
	}
}
