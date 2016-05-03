package me.wonwoo;

import me.wonwoo.config.RootConfiguration;
import me.wonwoo.domain.AccountTest;
import me.wonwoo.service.AccountService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wonwoo on 2016. 5. 3..
 */

public class TransactionTest {

  @Test
  public void test(){
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RootConfiguration.class);
    AccountService bean = annotationConfigApplicationContext.getBean(AccountService.class);
    AccountTest save = bean.save();
    System.out.println(save);
  }
}
