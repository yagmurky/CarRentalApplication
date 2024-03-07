package com.yagmur.service;

import com.yagmur.dto.request.ActivateStatusRequestDto;
import com.yagmur.dto.request.LoginRequestDto;
import com.yagmur.dto.request.PasswordUpdateRequestDto;
import com.yagmur.dto.request.RegisterRequestDto;
import com.yagmur.dto.response.RegisterResponseDto;
import com.yagmur.entity.Auth;
import com.yagmur.mapper.AuthMapper;
import com.yagmur.repository.AuthRepository;
import com.yagmur.utility.CodeGenerator;
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

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto){
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(registerRequestDto);
        auth.setActivationCode(CodeGenerator.generateCode());
        repository.save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }

    public Boolean login(LoginRequestDto loginRequestDto){
        Optional<Auth> authOptional=repository.findByUsernameAndPassword(loginRequestDto.getUsername(),loginRequestDto.getPassword());
        if(authOptional.isEmpty()) {
            return false;
        }
        if (!authOptional.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new RuntimeException("auth not active");
        }
        return true;
    }

    public Boolean activateStatus(ActivateStatusRequestDto activateStatusRequestDto){
        Optional<Auth> authOptional=repository.findByIdAndActivationCode(activateStatusRequestDto.getAuthId(), activateStatusRequestDto.getActivationCode());
        if(authOptional.isEmpty()) {
            return false;
        }
        authOptional.get().setStatus(EStatus.ACTIVE);
        repository.save(authOptional.get());
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

    public Boolean updatePassword(PasswordUpdateRequestDto passwordUpdateRequestDto){
        Optional<Auth> authOptional=repository.findByIdAndPassword(passwordUpdateRequestDto.getAuthId(), passwordUpdateRequestDto.getOldPassword());
        if(authOptional.isEmpty()) {
            return false;
        }
        authOptional.get().setPassword(passwordUpdateRequestDto.getNewPassword());
        repository.save(authOptional.get());
        return true;
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
