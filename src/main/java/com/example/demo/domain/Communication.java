package com.example.demo.domain;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Component
public class Communication {
	private String externalEmail;
	@Size(max=2000)
	private String emailText;
	private String commType;
	private String commFreeText;
}
