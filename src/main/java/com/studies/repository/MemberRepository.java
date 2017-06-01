package com.studies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studies.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
