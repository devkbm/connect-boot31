package com.like.cooperation.todo.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.system.core.jpa.domain.AbstractAuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true, exclude = {"todoGroup"})
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWTODO")
@EntityListeners(AuditingEntityListener.class)
public class Todo extends AbstractAuditEntity {		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long pkTodo;	

	@Column(name="TODO")
	String todo;		
	
	@Column(name="COMPLETE_YN")
	boolean isCompleted;
	
	@Column(name="DUE_DT")
    LocalDate dueDate;
	
	@Column(name="COMMENTS")
	String comments;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TODO_GROUP_ID", nullable=false)
	TodoGroup todoGroup;	
	
	@Builder
	public Todo(TodoGroup todoGroup, String todo, LocalDate dueDate, String comments) {
		this.todoGroup = todoGroup;
		this.todo = todo;
		this.dueDate = dueDate;
		this.comments = comments;
	}
	
	public void modify(String todo
					  ,boolean isCompleted
					  ,LocalDate dueDate
					  ,String comments) {
		this.todo = todo;
		this.isCompleted = isCompleted;
		this.dueDate = dueDate;
		this.comments = comments;		
	}
	
}
