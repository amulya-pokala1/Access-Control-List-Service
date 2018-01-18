import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.daoimpl.UserDAOImpl;
import com.accolite.miniau.accesscontrol.model.User;

public class UserDAOTest {
	static ApplicationContext context;
	static DataSource dataSource;
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);
	static UserDAO userdao;

	@BeforeClass
	public static void setUp() {
		// TODO--FILE NAME--RELATIVE PATH
		context = new FileSystemXmlApplicationContext(
				"C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Access-Control-List-service\\src\\main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		userdao = new UserDAOImpl();
		userdao.setDataSource(dataSource);
		BasicConfigurator.configure();
	}

	@Test
	public void testAddNewUser() {
		User user = new User("xyz", "dummy");
		userdao.addNewUser(user);
	}

	@Test
	public void testAddNewUserNameNull() {
		User user = new User(null, "dummy");
		userdao.addNewUser(user);
	}

	@Test
	public void testGetUser() {
		User user = userdao.getUser(11);
		logger.info("" + user.getUserId() + " " + user.getUserName());
	}
		
}
