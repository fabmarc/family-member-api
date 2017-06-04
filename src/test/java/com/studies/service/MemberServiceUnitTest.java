package com.studies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.studies.bean.MemberBean;
import com.studies.entity.Member;
import com.studies.exception.ApplicationException;
import com.studies.message.MessageEnum;
import com.studies.repository.MemberRepository;
import com.studies.util.MemberMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceUnitTest {

	@Autowired
	private MemberService memberService;

	@MockBean
	private MemberRepository memberRepository;

	@Test
	public void testFindExistentMember() throws ApplicationException {

		Member member = Member.builder().id(1).firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
		MemberBean memberExpected = MemberMapper.toBean(member);
		when(memberRepository.findOne(1)).thenReturn(member);
		MemberBean memberBean = memberService.findMember(1);
		assertEquals(memberExpected, memberBean);
	}

	@Test
	public void testFindInexistentMember() throws ApplicationException {
		try {
			memberService.findMember(-1);
			fail("Expected ApplicationException(code = REGISTER_NOT_FOUND)");
		} catch (ApplicationException e) {
			assertEquals(MessageEnum.REGISTER_NOT_FOUND.code(), e.getCode());
		}
	}

	@Test
	public void testFindMembersEmpty() {

		List<Member> membersExpected = new ArrayList<Member>(0);
		when(memberRepository.findAll()).thenReturn(null);
		List<MemberBean> membersBean = memberService.findMembers();
		assertEquals(membersExpected, membersBean);
	}

	@Test
	public void testFindMembersNotEmpty() {

		Member member1 = Member.builder().id(1).firstName("First Name 1").lastName("Last Name 1").birthDate(new Date()).build();
		Member member2 = Member.builder().id(2).firstName("First Name 2").lastName("Last Name 2").birthDate(new Date()).build();
		
		List<Member> members = new ArrayList<Member>(2);
		members.add(member1);
		members.add(member2);
		
		List<MemberBean> membersExpected = members.stream().map(MemberMapper::toBean).collect(Collectors.toList());
		
		when(memberRepository.findAll()).thenReturn(members);
		List<MemberBean> membersBean = memberService.findMembers();
		assertEquals(membersExpected, membersBean);
	}

}
