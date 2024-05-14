package com.ezen.www.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardVO {
    private long bno;
    private String title;
    private String writer;
    private String content;
    private String regAt;
    private String modAt;
}
