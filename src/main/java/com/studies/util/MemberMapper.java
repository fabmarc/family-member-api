package com.studies.util;

import com.studies.bean.MemberBean;
import com.studies.entity.Member;

public class MemberMapper {

	public static MemberBean toBean(Member member) {

		// with Lombok
		MemberBean memberBean = MemberBean.builder()
				.id(member.getId())
				.firstName(member.getFirstName())
				.lastName(member.getLastName())
				.birthDate(member.getBirthDate())
				.build();

		// with Java 8
		// MemberBean memberBean =
		// GenericBuilder.of(MemberBean::new).with(MemberBean::setId,
		// member.getId())
		// .with(MemberBean::setFirstName, member.getFirstName())
		// .with(MemberBean::setLastName, member.getLastName())
		// .with(MemberBean::setBirthDate, member.getBirthDate()).build();

		return memberBean;
	}

	public static Member toEntity(MemberBean memberBean) {

		// with Lombok
		Member member = Member.builder()
				.id(memberBean.getId())
				.firstName(memberBean.getFirstName())
				.lastName(memberBean.getLastName())
				.birthDate(memberBean.getBirthDate())
				.build();

		// with Java 8
		// Member member = GenericBuilder.of(Member::new)
		// .with(Member::setId, memberBean.getId())
		// .with(Member::setFirstName, memberBean.getFirstName())
		// .with(Member::setLastName, memberBean.getLastName())
		// .with(Member::setBirthDate, memberBean.getBirthDate())
		// .build();

		return member;
	}
}
