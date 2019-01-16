package com.se.ue04.service;

import com.se.ue04.model.Account;
import com.se.ue04.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    AccountRepository userRepository;

    @Autowired
    public void setUserRepository(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Account getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void saveUser(Account user) {
        userRepository.save(user);
    }

    public Account getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
