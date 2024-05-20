package com.ezen.www.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String email;
    private String password;
    private String regAt;
    private String lastLogin;
    private String nickName;
    private List<AuthVO> authList;
}
