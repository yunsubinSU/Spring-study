package com.example.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {

	@Autowired
	private DataSource dataSource3;

	@Bean 
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource3);
		
		// Mapper XML 파일의 위치 설정
		
		//이런 설정이 없으면 전체 경로를 설정해줘야하기 때문에 아래코드를 설정해주는 것이 좋다
	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource[] resources = resolver.getResources("classpath*:mapper/*.xml");
	    sessionFactory.setMapperLocations(resources);
	    
		return sessionFactory.getObject();
	}
	
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
		
	 @Bean
	 public SqlSessionTemplate sqlSessionTemplate() throws Exception {
	     return new SqlSessionTemplate(sqlSessionFactory());
	 }
	
}

