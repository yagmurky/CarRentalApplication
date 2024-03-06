package com.yagmur.service;

import com.yagmur.entity.Auth;
import com.yagmur.repository.AuthRepository;
import com.yagmur.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final AuthRepository repository;
    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository repository1) {
        super(repository);
        this.repository = repository1;
    }
}
