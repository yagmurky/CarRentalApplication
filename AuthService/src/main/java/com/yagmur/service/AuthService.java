package com.yagmur.service;

import com.yagmur.entity.Auth;
import com.yagmur.repository.AuthRepository;
import com.yagmur.utility.ServiceManager;
import com.yagmur.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final AuthRepository repository;
    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository repository1) {
        super(repository);
        this.repository = repository1;
    }

    public Boolean register(Auth auth){
        repository.save(auth);
        return true;
    }

    public Boolean login(String username, String password){
        Optional<Auth> auth=repository.findByUsernameAndPassword(username, password);
        if(auth.isEmpty()) {
            return false;
        }
        return true;
    }

    public void delete(Long id){
        Optional<Auth> auth=repository.findById(id);
        if(auth.isEmpty()) {
            throw new RuntimeException("auth not found");
        }else {
            auth.get().setStatus(EStatus.DELETED);
        }
        save(auth.get());
    }

    public Auth update(Auth auth){
        save(auth);
        return auth;
    }

    public Optional<Auth> findById(Long id){
        Optional<Auth> auth=repository.findById(id);
        if (auth.isEmpty()) {
            throw new RuntimeException("auth not found");
        }
        return auth;
    }

    public List<Auth> findAll(){
        return repository.findAll();
    }

}
