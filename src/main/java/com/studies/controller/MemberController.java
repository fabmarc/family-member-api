package com.studies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studies.bean.MemberBean;
import com.studies.exception.ApplicationException;
import com.studies.message.MessageEnum;
import com.studies.service.MemberService;

@RestController
@RequestMapping(path = "/svc/v1/controller")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping(path = "/member/{idMember}")
	public ResponseEntity<MemberBean> getMember(@PathVariable(name = "idMember") Integer id)
			throws ApplicationException {
		try {
			MemberBean memberBean = memberService.findMember(id);
			return new ResponseEntity<MemberBean>(memberBean, HttpStatus.OK);

		} catch (ApplicationException e) {
			return handleException(e);
		}
	}

	private <T> ResponseEntity<T> handleException(ApplicationException e) throws ApplicationException {

		if (MessageEnum.REGISTER_NOT_FOUND.code().equals(e.getCode())) {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		} else {
			throw e;
		}
	}

	@DeleteMapping(path = "/member/{idMember}")
	public ResponseEntity<Void> deleteMember(@PathVariable(name = "idMember") Integer id) throws ApplicationException {
		try {
			memberService.deleteMember(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (ApplicationException e) {
			return handleException(e);
		}
	}

	@GetMapping(path = "/members")
	public ResponseEntity<List<MemberBean>> getMembers() {

		List<MemberBean> memberBeans = memberService.findMembers();
		return new ResponseEntity<List<MemberBean>>(memberBeans, HttpStatus.OK);
	}

	@PostMapping(path = "/member")
	public ResponseEntity<MemberBean> addMember(@RequestBody MemberBean newMember) {

		MemberBean memberBean = memberService.addMember(newMember);
		return new ResponseEntity<MemberBean>(memberBean, HttpStatus.OK);
	}

	@PutMapping(path = "/member/{idMember}")
	public ResponseEntity<MemberBean> updateMember(@PathVariable Integer idMember, @RequestBody MemberBean newMember)
			throws ApplicationException {
		try {
			newMember.setId(idMember);
			MemberBean memberBean = memberService.updateMember(newMember);
			return new ResponseEntity<MemberBean>(memberBean, HttpStatus.OK);

		} catch (ApplicationException e) {
			return handleException(e);
		}
	}
}
