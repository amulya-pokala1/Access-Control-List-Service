/*
 * 
 */
package com.accolite.miniau.accesscontrol.utility;

// TODO: Auto-generated Javadoc
/**
 * The Class Query.
 */
public class Query {

	/**
	 * Instantiates a new query.
	 */
	private Query() {

	}

	/** The Constant CREATEADMIN. */
	// AdminDAO
	public static final String CREATEADMIN = "INSERT INTO ADMIN(ADMIN_NAME, DESCRIPTION, MAIL_ID) VALUES (?,?,?)";
	
	/** The Constant DELETEADMIN. */
	public static final String DELETEADMIN = "DELETE FROM ADMIN WHERE ADMIN_ID=?";
	
	/** The Constant CHANGEPASSWORD. */
	public static final String CHANGEPASSWORD = "UPDATE ADMIN SET PASSWORD=? WHERE ADMIN_ID=?";

	/** The Constant ADDNEWGROUP. */
	// GroupDAO
	public static final String ADDNEWGROUP = "INSERT INTO ACL.GROUP(GROUP_NAME,GROUP_DESCRIPTION) VALUES(?,?)";
	
	/** The Constant GETALLGROUPNAMES. */
	public static final String GETALLGROUPNAMES = "SELECT GROUP_NAME FROM ACL.GROUP";
	
	/** The Constant GETALLGROUPS. */
	public static final String GETALLGROUPS = "SELECT * FROM ACL.GROUP";
	
	/** The Constant GETUSERSINGROUP. */
	public static final String GETUSERSINGROUP = "SELECT * FROM USER JOIN USER_GROUP ON USER.USER_ID=USER_GROUP.USER_ID WHERE USER_GROUP.GROUP_ID=?";
	
	/** The Constant ADDUSERTOGROUP. */
	public static final String ADDUSERTOGROUP = "INSERT INTO USER_GROUP(GROUP_ID, USER_ID) VALUES(?,?)";
	
	/** The Constant REMOVEUSERFROMGROUP. */
	public static final String REMOVEUSERFROMGROUP = "DELETE FROM USER_GROUP WHERE USER_ID=? AND GROUP_ID=?";
	
	/** The Constant ADDPERMISSION. */
	public static final String ADDPERMISSION = "INSERT INTO GROUP_PERMISSION(GROUP_ID, PERMISSION_ID) VALUES(?,?)";
	
	/** The Constant REMOVEPERMISSION. */
	public static final String REMOVEPERMISSION = "DELETE FROM GROUP_PERMISSION WHERE GROUP_ID=? AND PERMISSION_ID=?";

	/** The Constant CREATEPERMISSION. */
	// PermissionDAO
	public static final String CREATEPERMISSION = "INSERT INTO PERMISSION(PERMISSION_NAME,PERMISSION_DESCRIPTION,IS_MANDATORY) VALUES (?,?,?)";
	
	/** The Constant DELETEPERMISSION. */
	public static final String DELETEPERMISSION = "DELETE FROM PERMISSION WHERE PERMISSION_ID = ?";
	
	/** The Constant GETALLPERMISSIONLIST. */
	public static final String GETALLPERMISSIONLIST = "SELECT * FROM PERMISSION";

	/** The Constant ADDNEWUSER. */
	// UserDAO
	public static final String ADDNEWUSER = "INSERT INTO USER(USER_NAME, MAIL_ID) VALUES (?,?)";
	
	/** The Constant GETUSER. */
	public static final String GETUSER = "SELECT * FROM USER WHERE USER_ID=?";
	
	/** The Constant GETALLUSERS. */
	public static final String GETALLUSERS = "SELECT * FROM USER";
	
	/** The Constant GETALLUSERNAMES. */
	public static final String GETALLUSERNAMES = "SELECT USER_NAME FROM USER";
	
	/** The Constant DELETEUSER. */
	public static final String DELETEUSER = "DELETE FROM USER WHERE USER_ID=?";
	
	/** The Constant ADDPERMISSIONTOUSER. */
	public static final String ADDPERMISSIONTOUSER = "INSERT INTO GROUP_PERMISSION(USER_ID, PERMISSION_ID) VALUES(?,?)";
	
	/** The Constant REMOVEPERMISSIONFROMUSER. */
	public static final String REMOVEPERMISSIONFROMUSER = "DELETE FROM USER_PERMISSION WHERE USER_ID=? AND PERMISSION_ID=?";
	
	/** The Constant UPDATEPASSWORD. */
	public static final String UPDATEPASSWORD = "UPDATE USER SET PASSOWORD=? WHERE USER_ID=?";
	
	/** The Constant GETUSERPERMISSIONS. */
	public static final String GETUSERPERMISSIONS = "SELECT * FROM USER_PERMISSION JOIN USER ON USER_PERMISSION.USER_ID=USER.USER_ID WHERE USER_ID=?";
}
