package com.yagmur.controller;

import com.yagmur.dto.request.ActivateStatusRequestDto;
import com.yagmur.dto.request.LoginRequestDto;
import com.yagmur.dto.request.PasswordUpdateRequestDto;
import com.yagmur.dto.request.RegisterRequestDto;
import com.yagmur.dto.response.RegisterResponseDto;
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
    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto auth) {
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
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(service.login(loginRequestDto));
    }

    @PostMapping(DELETE)
    public ResponseEntity<Void> delete(Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(UPDATE_PASSWORD)
    public ResponseEntity<Boolean> updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto){
        return ResponseEntity.ok(service.updatePassword(passwordUpdateRequestDto));
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(ActivateStatusRequestDto activateStatusRequestDto){
        return ResponseEntity.ok(service.activateStatus(activateStatusRequestDto));
    }

}
