package me.wonwoo;

import me.wonwoo.config.RootConfiguration;
import me.wonwoo.domain.AccountTest;
import me.wonwoo.service.AccountService;
import me.wonwoo.service.TransactionService;
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

@Test
public void transactionTest(){
  AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RootConfiguration.class);
  AccountService bean = annotationConfigApplicationContext.getBean(AccountService.class);
  AccountTest accountTest = new AccountTest();
  accountTest.setName("wonwoo");
  AccountTest save = bean.transactionTest(accountTest);
  System.out.println(save);
}
  @Test
  public void transactionTest1(){
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RootConfiguration.class);
    TransactionService bean = annotationConfigApplicationContext.getBean(TransactionService.class);

    bean.transactionService();

  }


}
