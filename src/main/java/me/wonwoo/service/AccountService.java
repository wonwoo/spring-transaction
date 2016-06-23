package me.wonwoo.service;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.domain.AccountTest;
import me.wonwoo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 * Created by wonwoo on 2016. 5. 3..
 */
@Service
@Slf4j
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private TransactionService transactionService;

//  아래는 롤백이 된다.

  //  롤백이 되지 않는다
//  @javax.transaction.Transactional
  @org.springframework.transaction.annotation.Transactional(readOnly = true)
  public AccountTest save() {

    AccountTest accountTest = save1();
    return accountTest;
  }

  @org.springframework.transaction.annotation.Transactional
  public AccountTest save1() {

    String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
    System.out.println(currentTransactionName);
    AccountTest accountTest = new AccountTest();
    accountTest.setName("wonwoo");
    accountRepository.save(accountTest);
//    if (true) {
//      throw new RuntimeException();
//    }
    return accountTest;
  }

@Transactional
public AccountTest transactionTest(AccountTest accountTest) {
  log.info("currentTransactionName : {}", TransactionSynchronizationManager.getCurrentTransactionName());
  transactionService.transactionService();
  AccountTest save = accountRepository.save(accountTest);
//  if (true) {
//    throw new RuntimeException();
//  }

  return save;
}
}
