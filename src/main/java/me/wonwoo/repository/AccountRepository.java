package me.wonwoo.repository;

import me.wonwoo.domain.AccountTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wonwoo on 2016. 5. 3..
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountTest, Long> {
}
