package kr.or.bit.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {
  @Autowired
  private ApplicationContext context;

  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/spring_sample?serverTimezone=UTC&useSSL=false");
    dataSource.setUsername("bituser");
    dataSource.setPassword("1004");
    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setConfigLocation(context.getResource("classpath:mybatis/config/mybatis-config.xml"));
    factoryBean.setMapperLocations(context.getResources("classpath:mybatis/mapper/*.xml"));

    return factoryBean.getObject();
  }

  @Bean
  public SqlSession sqlSession() throws Exception {
    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
    return sqlSessionTemplate;
  }
}
