package com.example.demo.domain;



import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "activity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Activity {
	
	@Id 
	private String id;
	private String visibilityScopekey;
	private String correlationId;
	private String summary;
	private String comments;
	private String priority;
	@NotNull(message="type must not be null")
	private String type;
	@NotNull(message="subtype must not be null")
	private String subType;
	@NotNull(message="channel must not be null")
	private String channel;
	@NotNull(message="status must not be null")
	private Status status;
	@Valid
	private Communication communication;
	private ActivityAudit audit;
	
}