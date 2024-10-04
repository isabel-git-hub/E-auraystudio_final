package com.example.AurayStudio.service;

import java.lang.reflect.Member;
import java.util.List;

import com.example.AurayStudio.dto.MemberDto;

public interface MemberService {
	boolean putMember(MemberDto dto);
	boolean checkId(String userid);
	MemberDto getMemberInfo(String userid);
	MemberDto editMemberInfo(MemberDto dto);
	void unregistUser(String userid);
	List<MemberDto> getMemberList();
	void deleteUser(String userid);
	void editUser(MemberDto dto);
	boolean checkMember(MemberDto dto);
	String getMemberById(MemberDto dto);
}
