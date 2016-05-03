package me.wonwoo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wonwoo on 2016. 5. 3..
 */

@Entity
@Data
public class AccountTest {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
}
