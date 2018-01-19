package com.accolite.miniau.accesscontrol.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.accolite.miniau.accesscontrol.dao.PermissionDAO;
import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.daoimpl.PermissionDAOImpl;
import com.accolite.miniau.accesscontrol.daoimpl.UserDAOImpl;
import com.accolite.miniau.accesscontrol.model.Permission;
import com.accolite.miniau.accesscontrol.model.User;

public class UserDAOTest {
	static ApplicationContext context;
	static DataSource dataSource;
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);
	static UserDAO userdao;
	static PermissionDAO permissiondao;

	@BeforeClass
	public static void setUp() {

		context = new FileSystemXmlApplicationContext(
				"C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Access-Control-List-service\\src\\main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		userdao = new UserDAOImpl();
		userdao.setDataSource(dataSource);
		permissiondao = new PermissionDAOImpl();
		permissiondao.setDataSource(dataSource);
		BasicConfigurator.configure();
	}

	@Test
	public void testAddNewUser() {
		User user = new User("test", "test");
		boolean result = userdao.addNewUser(user);
		assertTrue(result);
		userdao.deleteUser(user.getUserId());

	}

	@Test
	public void testAddNewUserNameNull() {
		User user = new User(null, "dummy");
		boolean result = userdao.addNewUser(user);
		assertFalse(result);
	}

	@Test
	public void testGetUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		User userGet = userdao.getUser(user.getUserId());
		assertEquals("test", userGet.getMailId());
		assertEquals("test", userGet.getUserName());
		logger.info(userGet.toString());
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testGetUserError() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		User userGet = userdao.getUser(user.getUserId() + 1);
		assertNull(userGet);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testDeleteUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		boolean result = userdao.deleteUser(user.getUserId());
		assertTrue(result);
	}

	@Test
	public void testDeleteUserError() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		boolean result = userdao.deleteUser(user.getUserId() + 1);
		userdao.deleteUser(user.getUserId());
		assertFalse(result);
	}

	@Test
	public void testGetAllUsers() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		int count = 1;
		List<User> users = userdao.getAllUsers();
		for (User userIter : users) {
			if (userIter.getMailId().equals(user.getMailId())) {
				count--;
			}
		}
		assertEquals(0, count);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testGetAllUserNames() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		List<String> userNames = userdao.getAllUserNames();
		assertTrue(userNames.contains(user.getUserName()));
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testUpdatePassword() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		boolean result = userdao.updatePassword(user.getUserId(), "test");
		assertTrue(result);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testUpdatePasswordNoUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		boolean result = userdao.updatePassword(user.getUserId() + 1, "dont_know");
		assertFalse(result);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testAddPermissionToUserNoUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		boolean result = userdao.addPermissionToUser(user.getUserId() + 1, 1);
		assertFalse(result);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testAddPermissionToUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		boolean result = userdao.addPermissionToUser(user.getUserId(), 1);
		assertTrue(result);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testGetPermissionOfUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		List<Permission> permissions = userdao.getPermissionOfUser(user.getUserId());
		assertNotNull(permissions);
		userdao.deleteUser(user.getUserId());
	}

	@Test
	public void testRemovePermissionOfUser() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		Permission permission = new Permission("test", "test", false);

		boolean result = permissiondao.createPermission(permission);
		assertTrue(result);
		userdao.addPermissionToUser(user.getUserId(), permission.getPermissionId());
		result = userdao.removePermissionFromUser(user.getUserId(), permission.getPermissionId());
		assertTrue(result);
		userdao.deleteUser(user.getUserId());
		permissiondao.deletePermission(permission.getPermissionId());
	}

	@Test
	public void testRemovePermissionOfUserError() {
		User user = new User("test", "test");
		userdao.addNewUser(user);
		Permission permission = new Permission("test", "test", false);
		permissiondao.createPermission(permission);
		boolean result = userdao.removePermissionFromUser(user.getUserId(), permission.getPermissionId() + 1);
		assertFalse(result);
		userdao.deleteUser(user.getUserId());
		permissiondao.deletePermission(permission.getPermissionId());
	}

}
