package com.se.ue04.repository;

import com.se.ue04.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, String> {
    Account findByEmailAndPassword(String email, String password);
    Account findByEmail(String email);

}
