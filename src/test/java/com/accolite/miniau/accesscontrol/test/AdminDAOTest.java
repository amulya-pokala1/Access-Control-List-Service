package com.accolite.miniau.accesscontrol.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
				"C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Access-Control-List-service\\src\\main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
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
	public void testSetPassword() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		boolean result = admindao.updatePassword(admin.getAdminId(), "test");
		assertTrue(result);
		admindao.deleteAdmin(admin.getAdminId());
	}

	@Test
	public void testSetPasswordNoUser() {
		Admin admin = new Admin("test", "test", "test");
		admindao.createAdmin(admin);
		boolean result = admindao.updatePassword(admin.getAdminId() + 1, "test");
		logger.info(result);
		admindao.deleteAdmin(admin.getAdminId());
	}

}
