package com.sbs.sbsgroup7.DataSource;

import com.sbs.sbsgroup7.model.Transaction;
import com.sbs.sbsgroup7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransRepository extends JpaRepository<Transaction, String> {

   /* Optional<User> findByEmail(String username);
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String username);
    Optional<User> findOneByEmailIgnoreCase(String email);
    Optional<User>  findOneBySsn(int ssn);
    Optional<User> findOneByPhone(int phone);
    Optional<User>  findOneByEmailIgnoreCaseOrSsnOrPhone(String email, String ssn, String phone);

    */

}