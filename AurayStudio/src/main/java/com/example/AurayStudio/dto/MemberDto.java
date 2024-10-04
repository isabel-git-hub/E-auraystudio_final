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
public class MemberDto {
	private String userid;
	private String userpw;
    private String username;
    private String birthdate;
    private String gender;
    private String telnumber;
    private String addr;
    private int permit;
    private String email;
    
}
