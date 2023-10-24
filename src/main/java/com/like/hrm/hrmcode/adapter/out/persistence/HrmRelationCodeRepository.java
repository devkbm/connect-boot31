package com.like.hrm.hrmcode.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.domain.HrmRelationCode;

@Repository
public interface HrmRelationCodeRepository extends JpaRepository<HrmRelationCode, Long> {

}
