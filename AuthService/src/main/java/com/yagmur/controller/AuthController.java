package com.yagmur.controller;

import com.yagmur.entity.Auth;
import com.yagmur.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.yagmur.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService service;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(Auth auth) {
       return ResponseEntity.ok(service.register(auth));
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Auth> update(Auth auth) {
        return ResponseEntity.ok(service.update(auth));
    }

    @PostMapping(FIND_BY_ID)
    public ResponseEntity<Auth> findById(Long id) {
        return ResponseEntity.ok(service.findById(id).get());
    }

    @PostMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody String username, String password) {
        return ResponseEntity.ok(service.login(username, password));
    }

    @PostMapping(DELETE)
    public ResponseEntity<Void> delete(Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
