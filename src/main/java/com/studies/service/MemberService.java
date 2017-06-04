package com.studies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.studies.bean.MemberBean;
import com.studies.entity.Member;
import com.studies.exception.ApplicationException;
import com.studies.exception.ApplicationExceptionBuilder;
import com.studies.message.MessageEnum;
import com.studies.repository.MemberRepository;
import com.studies.util.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberReposity;
	
	@Autowired
	private ApplicationExceptionBuilder applicationException;

	public MemberBean findMember(Integer id) throws ApplicationException {

		MemberBean memberBean = null;
		Member theMember = memberReposity.findOne(id);

		if (theMember == null) {
			throw applicationException.create(MessageEnum.REGISTER_NOT_FOUND, "Member");
		}
		memberBean = MemberMapper.toBean(theMember);
		return memberBean;
	}

	public void deleteMember(Integer id) throws ApplicationException {
		try {
			memberReposity.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw applicationException.create(e, MessageEnum.REGISTER_NOT_FOUND, "Member");
		}
	}

	public List<MemberBean> findMembers() {

		List<MemberBean> memberBeans = null;
		List<Member> theMembers = memberReposity.findAll();

		if (theMembers != null) {
			memberBeans = theMembers.stream().map(MemberMapper::toBean).collect(Collectors.toList());
		} else {
			memberBeans = new ArrayList<>(0);
		}
		return memberBeans;
	}

	public MemberBean addMember(MemberBean newMember) {

		Member member = MemberMapper.toEntity(newMember);
		member.setId(null);
		
		Member theMember = memberReposity.save(member);
		MemberBean memberBean = MemberMapper.toBean(theMember);

		return memberBean;
	}

	
	public MemberBean updateMember(MemberBean newMember) throws ApplicationException {

		boolean found = memberReposity.exists(newMember.getId());
		if (!found) {
			throw applicationException.create(MessageEnum.REGISTER_NOT_FOUND, "Member");
		}
		Member member = MemberMapper.toEntity(newMember);
		Member theMember = memberReposity.save(member);
		MemberBean memberBean = MemberMapper.toBean(theMember);
		
		return memberBean;
	}
}
