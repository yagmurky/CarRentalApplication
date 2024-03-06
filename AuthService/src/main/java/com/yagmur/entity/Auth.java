package com.yagmur.entity;

import com.yagmur.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_auth")
@Entity
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String tcNo;
    private String phoneNumber;
    private String email;
    private String idCardUrl;

    @Builder.Default
    private EStatus status = EStatus.PENDING;


}
