package me.wonwoo.service;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.domain.AccountTest;
import me.wonwoo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by wonwoo on 2016. 6. 23..
 */
@Service
@Slf4j
public class TransactionService {

  @Autowired
  private AccountRepository accountRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void transactionService() {
    log.info("currentTransactionName : {}", TransactionSynchronizationManager.getCurrentTransactionName());
    AccountTest accountTest = new AccountTest();
    accountTest.setName("wonwoo123");
    accountRepository.save(accountTest);

  }
}
