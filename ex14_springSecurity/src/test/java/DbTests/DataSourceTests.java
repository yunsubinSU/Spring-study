package DbTests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.app.domain.dao.MemoDaoImpl;
import com.example.app.domain.dto.MemoDto;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class DataSourceTests {

	@Autowired
	private DataSource dataSource1;
	
	@Autowired
	private DataSource dataSource2;
	
	
	@Autowired
	private MemoDaoImpl memoDaoImpl;
	
	@Test
	@Disabled
	void test1() throws Exception {
		System.out.println(dataSource1);
		Connection con =  dataSource1.getConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('abcd','abcd','abcd','abcd')");	
		pstmt.executeUpdate();
	}
	@Test
	@Disabled
	void test2() throws Exception {
		memoDaoImpl.insert(new MemoDto(1,"a","a",LocalDateTime.now()));
	}
	
	@Test
	@Disabled
	void test3() throws Exception {
		System.out.println(dataSource2);
		Connection con =  dataSource2.getConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('ffff','ffff','ffff','ffff')");	
		pstmt.executeUpdate();
	}
	@Autowired
	private DataSource dataSource3;
	@Test
	void test4() throws Exception {
		System.out.println(dataSource3);
		Connection con =  dataSource3.getConnection();
		PreparedStatement pstmt = con.prepareStatement("insert into tbl_book values('fqqwff','ffff','ffff','ffff')");	
		pstmt.executeUpdate();
	}
}


