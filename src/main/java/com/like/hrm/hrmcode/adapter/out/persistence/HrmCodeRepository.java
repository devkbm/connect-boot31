package com.like.hrm.hrmcode.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeId;

@Repository
public interface HrmCodeRepository extends JpaRepository<HrmCode, HrmCodeId> {

}
