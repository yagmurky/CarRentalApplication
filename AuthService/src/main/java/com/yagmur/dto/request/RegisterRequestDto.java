package com.yagmur.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {

    @Column(unique = true)
    @Size(min = 3, max = 8)
    private String username;
    @Size(min = 8, max = 16)
    private String password;
    @Column(unique = true)
    @Size(min = 11, max = 11)
    private String tcNo;
    @Size(min = 10, max = 10, message = "Başında 0 olmadan lütfen telefon numaranızı yazınız.")
    private String phoneNumber;
    @Email
    private String email;
}
