package com.like.system.webresource.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.system.webresource.domain.WebResource;

@Repository
public interface WebResourceJpaRepository extends JpaRepository<WebResource, String>, ListQuerydslPredicateExecutor<WebResource> {

}
