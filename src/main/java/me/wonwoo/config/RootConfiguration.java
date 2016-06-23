package me.wonwoo.config;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wonwoo on 2016. 5. 3..
 */

@Configuration
@EnableJpaRepositories(basePackages = "me.wonwoo")
@EnableTransactionManagement
@ComponentScan(
  basePackages = "me.wonwoo"
)
public class RootConfiguration {

  @Bean
  public DataSource dataSource() {
    JdbcDataSource jdbcDataSource = new JdbcDataSource();
    jdbcDataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
    jdbcDataSource.setUser("sa");

//    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//    EmbeddedDatabase db = builder
//      .setType(EmbeddedDatabaseType.H2)
//      .build();
    return jdbcDataSource;
  }

  @Bean
  public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
    return entityManagerFactory.createEntityManager();
  }

  @Bean
  public HibernateJpaDialect hibernateJpaDialect(){
    return new HibernateJpaDialect();
  }

  @Bean
  public FactoryBean<EntityManagerFactory> entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    containerEntityManagerFactoryBean.setDataSource(dataSource());
    JpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
    containerEntityManagerFactoryBean.setJpaVendorAdapter(adaptor);
    containerEntityManagerFactoryBean.setPackagesToScan("me.wonwoo");
    Map<String,Object> props = new HashMap<String, Object>();
    props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//    props.put("jpaDialect",hibernateJpaDialect());

    props.put("hibernate.show_sql", "true");
    props.put("hibernate.hbm2ddl.auto", "create");
    containerEntityManagerFactoryBean.setJpaPropertyMap(props);
    return containerEntityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
    return jpaTransactionManager;
  }
}
