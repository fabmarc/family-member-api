package com.studies.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MemberBean {

	private Integer id;

	private String firstName;

	private String lastName;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	private FamilyBean family;

}
