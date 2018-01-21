package com.accolite.miniau.accesscontrol.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.accolite.miniau.accesscontrol.dao.PermissionDAO;
import com.accolite.miniau.accesscontrol.daoimpl.PermissionDAOImpl;
import com.accolite.miniau.accesscontrol.model.Permission;

public class PermissionDAOTest {

	static ApplicationContext context;
	static DataSource dataSource;
	static PermissionDAO permissiondao;

	@BeforeClass
	public static void setUp() {

		context = new FileSystemXmlApplicationContext(
				"C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Access-Control-List-service\\src\\main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		permissiondao = new PermissionDAOImpl();
		permissiondao.setDataSource(dataSource);

	}

	@Test
	public void testCreatePermission() {
		Permission permission = new Permission("test", "test");
		boolean result = permissiondao.createPermission(permission);
		assertTrue(result);
		permissiondao.deletePermission(permission.getPermissionId());

	}

	@Test
	public void testCreatePermissionExists() {
		Permission permission = new Permission(null, "test");
		boolean result = permissiondao.createPermission(permission);
		permissiondao.deletePermission(permission.getPermissionId());
		assertFalse(result);

	}

	@Test
	public void testDeletePermission() {

		Permission permission = new Permission("test", "test");
		permissiondao.createPermission(permission);
		boolean result = permissiondao.deletePermission(permission.getPermissionId());
		assertTrue(result);

	}

	@Test
	public void testGetAllPermissions() {
		Permission permission = new Permission("test", "test");
		permissiondao.createPermission(permission);
		List<Permission> permissions = permissiondao.getAllPermissionList();
		int count = 1;
		for (Permission permissionIter : permissions) {
			if (permissionIter.getPermissionName().equals(permission.getPermissionName())) {
				count--;
			}
		}
		assertEquals(0, count);
		permissiondao.deletePermission(permission.getPermissionId());
	}
}
