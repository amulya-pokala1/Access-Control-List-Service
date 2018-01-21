package com.accolite.miniau.accesscontrol.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.accolite.miniau.accesscontrol.dao.AdminDAO;
import com.accolite.miniau.accesscontrol.daoimpl.AdminDAOImpl;
import com.accolite.miniau.accesscontrol.model.Admin;

public class AdminDAOTest {
	static ApplicationContext context;
	static DataSource dataSource;
	static AdminDAO admindao;
	private static final Logger logger = Logger.getLogger(AdminDAOTest.class);

	@BeforeClass
	public static void setUp() {
		context = new FileSystemXmlApplicationContext(
				"C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Access-Control-List-Service\\src\\main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
		// context=new
		// ClassPathXmlApplicationContext("main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		admindao = new AdminDAOImpl();
		admindao.setDataSource(dataSource);
		BasicConfigurator.configure();
	}

	@Test
	public void createAdmin() {
		Admin admin = new Admin("test", "test", "test");
		boolean result = admindao.createAdmin(admin);
		assertTrue(result);
		logger.info("admin id: " + admin.getAdminId());
		admindao.deleteAdmin(admin.getAdminId());

	}

	@Test
	public void createAdminExists() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		Admin admin1 = new Admin("test", "test", "test");
		boolean result = admindao.createAdmin(admin1);
		assertFalse(result);
		logger.info("admin id: " + admin.getAdminId());
		admindao.deleteAdmin(admin.getAdminId());
		admindao.deleteAdmin(admin1.getAdminId());

	}

	@Test
	public void deleteAdmin() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		boolean result = admindao.deleteAdmin(admin.getAdminId());
		assertTrue(result);

	}

	@Test
	public void testGetAdminName() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		String name = admindao.getAdminName(admin.getAdminId());
		assertEquals(admin.getAdminName(), name);
		admindao.deleteAdmin(admin.getAdminId());

	}

	@Test
	public void testGetAdminNameError() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		String name = admindao.getAdminName(admin.getAdminId() + 1);
		assertNotEquals(admin.getAdminName(), name);
		admindao.deleteAdmin(admin.getAdminId());
	}

	@Test
	public void testGetAdminIdUsingEmail() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		int id = admindao.getAdminIdUsingEmail(admin.getMailId());
		assertEquals(admin.getAdminId(), id);
		admindao.deleteAdmin(admin.getAdminId());
	}

	@Test
	public void testGetAdminIdUsingEmailError() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		int id = admindao.getAdminIdUsingEmail(admin.getMailId() + "c");
		assertNotEquals(admin.getAdminId(), id);
		admindao.deleteAdmin(admin.getAdminId());
	}

	@Test
	public void testGetAllAdmins() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		List<Admin> admins = admindao.getAllAdmins();
		int count = 1;
		for (Admin adminIter : admins) {
			if (adminIter.getAdminName().equals(admin.getAdminName())) {
				count--;
			}

		}
		admindao.deleteAdmin(admin.getAdminId());
		assertEquals(0, count);

	}

	@Test
	public void testSendPasswordLink() {
		Admin admin = new Admin("test", "test", "test@gmail.com");
		admindao.createAdmin(admin);
		admindao.setDataSourceForURIUtil(dataSource);
		admindao.sendPasswordLink("test@gmail.com", "127.0.0.1", 8080);
		int adminId = admindao.getAdminIdFromURI(admindao.getURI());
		assertEquals(adminId, admin.getAdminId());
		boolean result = admindao.updatePassword(admindao.getURI(), "test");
		assertTrue(result);
		admindao.deleteAdmin(admin.getAdminId());
	}

}
