package com.studies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.studies.bean.MemberBean;
import com.studies.exception.ApplicationException;
import com.studies.message.MessageEnum;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceIntegrationTest {

	@Autowired
	private MemberService memberService;

	@Test
	public void testAddInexistentMember() throws ApplicationException {

		MemberBean memberExpected = MemberBean.builder().firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
		MemberBean memberBean = memberService.addMember(memberExpected);

		assertNotNull(memberBean.getId());
		assertEquals(memberExpected.getFirstName(), memberBean.getFirstName());
		assertEquals(memberExpected.getLastName(), memberBean.getLastName());
		assertEquals(memberExpected.getBirthDate(), memberBean.getBirthDate());
	}

	@Test
	public void testAddMemberWithExistentId() throws ApplicationException {

		MemberBean memberExpected = MemberBean.builder().firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
		MemberBean memberBean = memberService.addMember(memberExpected);

		memberExpected = MemberBean.builder().id(memberBean.getId()).firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
		memberBean = memberService.addMember(memberExpected);

		assertNotNull(memberBean.getId());
		assertNotEquals(memberExpected.getId(), memberBean.getId());
		assertEquals(memberExpected.getFirstName(), memberBean.getFirstName());
		assertEquals(memberExpected.getLastName(), memberBean.getLastName());
		assertEquals(memberExpected.getBirthDate(), memberBean.getBirthDate());
	}

	@Test
	public void testDeleteExistentMember() throws ApplicationException {

		MemberBean member = MemberBean.builder().firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
		member = memberService.addMember(member);

		memberService.deleteMember(member.getId());
		try {
			memberService.findMember(member.getId());
			fail("Expected ApplicationException(code = REGISTER_NOT_FOUND)");
		} catch (ApplicationException e) {
			assertEquals(MessageEnum.REGISTER_NOT_FOUND.code(), e.getCode());
		}
	}

	@Test
	public void testDeleteInexistentMember() throws ApplicationException {
		try {
			memberService.deleteMember(-1);
			fail("Expected ApplicationException(code = REGISTER_NOT_FOUND)");
		} catch (ApplicationException e) {
			assertEquals(MessageEnum.REGISTER_NOT_FOUND.code(), e.getCode());
		}
	}

	@Test
	public void testUpdateExistentMember() throws ApplicationException {

		MemberBean memberExpected = MemberBean.builder().firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
		memberExpected = memberService.addMember(memberExpected);
		memberExpected.setFirstName("First Name First Name");
		memberExpected.setLastName("Last Name Last Name");
		memberExpected.setBirthDate(new Date());

		MemberBean memberBean = memberService.updateMember(memberExpected.getId(), memberExpected);
		assertEquals(memberExpected, memberBean);
	}

	@Test
	public void testUpdateInexistentMember() throws ApplicationException {
		try {
			MemberBean member = MemberBean.builder().id(-1).firstName("First Name").lastName("Last Name").birthDate(new Date()).build();
			memberService.updateMember(-1, member);
			fail("Expected ApplicationException(code = REGISTER_NOT_FOUND)");
		} catch (ApplicationException e) {
			assertEquals(MessageEnum.REGISTER_NOT_FOUND.code(), e.getCode());
		}
	}
	
}
