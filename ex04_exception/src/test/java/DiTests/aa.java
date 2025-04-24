package DiTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.config.PersonComponent;
import com.example.app.domain.dto.PersonDto;

// 유닛테이스 부터하고 가야함
@ExtendWith(SpringExtension.class) //제이뉴닛 버전 5
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class aa {

	@Autowired
	private PersonDto personDto1;
	
	@Autowired
	private PersonDto personDto2;
	
	@Autowired
	private PersonDto person03;
	
	@Autowired
	private PersonDto personBean;
	
	@Autowired
	private PersonComponent personComponent;
	
	@Test
	public void test() {
//		System.out.println(personDto1);
//		System.out.println(personDto2);
//		System.out.println(person03);
//		System.out.println(personBean);
//		System.out.println(personComponent);
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void test2() {
		
		assertNotNull(applicationContext);
		System.out.println(applicationContext.getBean("personDto1"));
		System.out.println(applicationContext.getBean("personDto2"));
		
	}
}
