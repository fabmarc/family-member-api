package com.studies.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.studies.entity.Member;
import com.studies.repository.MemberRepository;
import com.studies.util.MemberMapper;

@RestController
@RequestMapping(path = "/svc/v1/controller")
public class MemberController {

	@Autowired
	private MemberRepository memberReposity;

	@GetMapping(path = "/member/{idMember}")
	public ResponseEntity<MemberBean> getMember(@PathVariable(name = "idMember") Integer id) {

		MemberBean memberBean = null;
		Member theMember = memberReposity.findOne(id);

		if (theMember == null) {
			return new ResponseEntity<MemberBean>(HttpStatus.NOT_FOUND);
		}
		memberBean = MemberMapper.toBean(theMember);
		return new ResponseEntity<MemberBean>(memberBean, HttpStatus.OK);
	}

	@DeleteMapping(path = "/member/{idMember}")
	public ResponseEntity<Void> deleteMember(@PathVariable(name = "idMember") Integer id) {

		try {
			memberReposity.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping(path = "/members")
	public ResponseEntity<List<MemberBean>> getMembers() {

		List<MemberBean> memberBeans = null;
		List<Member> theMembers = memberReposity.findAll();

		if (theMembers != null) {
			memberBeans = theMembers.stream().map(MemberMapper::toBean).collect(Collectors.toList());
		}
		return new ResponseEntity<List<MemberBean>>(memberBeans, HttpStatus.OK);
	}

	@PostMapping(path = "/member")
	public MemberBean addMember(@RequestBody MemberBean newMember) {

		Member member = MemberMapper.toEntity(newMember);
		Member theMember = memberReposity.save(member);
		MemberBean memberBean = MemberMapper.toBean(theMember);

		return memberBean;
	}

	
	@PutMapping(path = "/member/{idMember}")
	public ResponseEntity<MemberBean> updateMember(@PathVariable Integer idMember, @RequestBody MemberBean newMember) {

		Member theMember = memberReposity.findOne(idMember);
		if (theMember == null) {
			return new ResponseEntity<MemberBean>(HttpStatus.NOT_FOUND);
		}
		Member member = MemberMapper.toEntity(newMember);
		member.setId(idMember);
		
		theMember = memberReposity.save(member);
		
		MemberBean memberBean = MemberMapper.toBean(theMember);
		return new ResponseEntity<MemberBean>(memberBean, HttpStatus.OK);
	}
}
