package test.jd.payment.risk.benz;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.jd.payment.risk.benz.dao.ds.PreheatDao;

/**
 * <p>Title: TestJeepDao</p>
 * <p>Description: </p>
 * <p>Company: ChinaBank</p>
 * @ClassName: TestJeepDao
 * @author wyheguangdong
 * @date 2013年10月20日 上午11:36:46
 * @version 1.0.0
 */
@ContextConfiguration(locations={"/spring/spring-config-common-hummer-benz.xml"})
public class PreheatDaoTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private PreheatDao preheatDao;
	
	@Test
	public void testSelect() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", "SELECT count(1) FROM tbl_qpay_day_amount WHERE id = 1;");
		int value = preheatDao.select(map);
		System.out.println("==> PreheatDao testSelect: " + value);
	}
	
	@Test
	public void testInsert(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", "INSERT INTO tbl_qpay_day_amount(bankcard_no, amount, trade_time, created_day, created_month, created_year,created) VALUES ('9000200033665', 20, 1384915038, 20, 11, 2013,'2013-12-12');");
		int value = preheatDao.insert(map);
		System.out.println("==> PreheatDao testInsert: " + value);
	}
	
}
