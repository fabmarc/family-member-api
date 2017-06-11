package com.studies.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MemberBean {

	private Integer id;

	@NotNull @NotBlank
	@Size(min = 1, max = 25)
	private String firstName;

	@NotNull @NotBlank
	@Size(min = 1, max = 25)
	private String lastName;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;

	private FamilyBean family;

}
