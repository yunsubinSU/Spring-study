package DbTests;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class DataSourceTests {

	@Autowired
	private DataSource dataSource1;
	
	 @Test
	 void test1() throws Exception {
		 System.out.println(dataSource1);
		 Connection con = dataSource1.getConnection();
		 PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('abcd','abcd','abcd','abcd')");
	 }

}
