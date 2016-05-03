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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
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
  public FactoryBean<EntityManagerFactory> entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    containerEntityManagerFactoryBean.setDataSource(dataSource());
    JpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
    containerEntityManagerFactoryBean.setJpaVendorAdapter(adaptor);
    containerEntityManagerFactoryBean.setPackagesToScan("me.wonwoo");
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.hbm2ddl.auto", "create");
    containerEntityManagerFactoryBean.setJpaProperties(props);
    return containerEntityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
    return jpaTransactionManager;
  }
}
