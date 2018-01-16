package com.accolite.miniau.accesscontrol.utility;

public class Query {

	// AdminDAO
	public static String createAdmin = "INSERT INTO ACL.ADMIN(ADMIN_ID,ADMIN_NAME,PASSWORD, DESCRIPTION) VALUES (?,?,?,?)";
	public static String deleteAdmin = "DELETE FROM ACL.ADMIN WHERE ADMINID=?";
	public static String changePassword = "UPDATE ACL.ADMIN SET PASSWORD=? WHERE ADMINID=?";

	// GroupDAO
	public static String addNewGroup = "INSERT INTO GROUP(GROUP_ID, GROUP_NAME) VALUES(?,?)";
	public static String getAllGroupNames = "SELECT GROUP_NAME FROM GROUP";
	public static String getAllGroups = "SELECT * FROM GROUP";
	public static String getUsersInGroup = "SELECT * FROM USER JOIN USER_GROUP ON USER.USER_ID=USER_GROUP.USER_ID WHERE USER_GROUP.GROUP_ID=?";
	public static String addUserToGroup = "INSERT INTO ACL.USER_GROUP(GROUPID, USERID) VALUES(?,?)";
	public static String removeUserFromGroup = "DELETE FROM USER_GROUP WHERE USERID=? AND GROUPID=?";
	public static String addPermission = "INSERT INTO ACL.GROUP_PERMISSION(GROUPID, PERMISSIONID) VALUES(?,?)";
	public static String removePermission = "DELETE FROM ACL.GROUP_PERMISSION WHERE GROUPID=? AND PERMISSIONID=?";

	// PermissionDAO
	public static String createPermission = "INSERT INTO PERMISSION(PERMISSION_NAME,PERMISSION_DESCRIPTION) VALUES (?,?)";
	public static String deletePermission = "DELETE FROM PERMISSION WHERE PERMISSION_ID = ?";
	public static String getAllPermissionList = "SELECT * FROM PERMISSION";

	// UserDAO
	public static String addNewUser = "INSERT INTO USER(USERID, USERNAME, PASSWORD) VALUES (?,?,?)";
	public static String getUser = "SELECT * FROM USER WHERE USERID=?";
	public static String getAllUsers = "SELECT * FROM USER";
	public static String getAllUserNames = "SELECT USERNAME FROM USER";
	public static String addPermissionToUser = "INSERT INTO ACL.GROUP_PERMISSION(USERID, PERMISSIONID) VALUES(?,?)";
	public static String removePermissionFromUser = "DELETE FROM ACL.USER_PERMISSION WHERE USERID=? AND PERMISSIONID=?";

}
