package com.example.demo.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "activity-attribute")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityAudit {

	@CreatedBy
	private String createdBy;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@JsonFormat(shape=Shape.STRING)
	@CreatedDate
	private Date createdDate;
	private String updatedBy;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@LastModifiedDate
	@JsonFormat(shape=Shape.STRING)
	private Date updatedDate;
}