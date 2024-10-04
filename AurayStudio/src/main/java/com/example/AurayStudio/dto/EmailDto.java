package com.example.AurayStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {
	private String to; // 수신자 이메일 
	/* private String from; */
    private String subject; // 이메일 제목
    private String text; // 이메일 본문
}
