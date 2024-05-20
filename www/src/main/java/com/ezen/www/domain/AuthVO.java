package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Service
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthVO {
    private String email;
    private String auth;
}
