import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 */

/**
 * @Since : Aug 24, 2020 
 * @Author kimhyunjin
 * <pre>
 * -----------------
 * 개정이력
 * Aug 24, 2020 kimhyunjin : 최초작성 
 * </pre>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
(locations = {"file:src/main/webapp/WEB-INF/spring/applicationConfig.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestConfigure {
	@Autowired
	private ApplicationContext ctx;
	
	@Before
	public void beforeClass() {
		System.out.println("-----테스트 시작-----");
	}
	
	@After
	public void afterClass() {
		System.out.println("-----테스트 종료-----");
	}

}
