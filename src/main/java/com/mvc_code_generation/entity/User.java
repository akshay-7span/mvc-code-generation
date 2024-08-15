package com.mvc_code_generation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isActive;
    private String otp;
    private boolean isVerified;
}
