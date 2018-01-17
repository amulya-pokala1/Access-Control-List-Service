import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.accolite.miniau.accesscontrol.dao.UserDAO;
import com.accolite.miniau.accesscontrol.dao.UserDAOImpl;
import com.accolite.miniau.accesscontrol.model.User;
import javax.sql.DataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class UserDAOTest {
	static ApplicationContext context;
	static DataSource dataSource ;
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);
	static UserDAO userdao;
	@BeforeClass
	public static void setUp()
	{
		context=new FileSystemXmlApplicationContext(
				"C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Access-Control-List-service\\src\\main\\webapp\\WEB-INF\\springDispatcherServlet-servlet.xml");
		dataSource = (DataSource) context.getBean("dataSource");
		userdao = new UserDAOImpl();
		userdao.setDataSource(dataSource);
		BasicConfigurator.configure();
		
	}
	
	@Test
	public void testAddNewUser()
	{
		User user = new User("xyz","dummy","dummy");
		userdao.addNewUser(user);
	}
	
	@Test
	public void testAddNewUserNameNull()
	{
		User user = new User(null,"dummy","dummy");
		userdao.addNewUser(user);
	}
	
	@Test
	public void testGetUser()
	{
		User user=userdao.getUser(6);
		logger.info(""+user.getUserId()+" "+user.getUserName());
		
	}

}
