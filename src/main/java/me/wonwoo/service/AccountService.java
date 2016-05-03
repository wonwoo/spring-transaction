package me.wonwoo.service;

import me.wonwoo.domain.AccountTest;
import me.wonwoo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wonwoo on 2016. 5. 3..
 */
@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

//  아래는 롤백이 된다.
//  @org.springframework.transaction.annotation.Transactional
//  롤백이 되지 않는다
  @javax.transaction.Transactional
  public AccountTest save() {
    AccountTest accountTest = new AccountTest();
    accountTest.setName("wonwoo");
    accountRepository.save(accountTest);
    if (true) {
      throw new RuntimeException();
    }
    return accountTest;
  }
}
