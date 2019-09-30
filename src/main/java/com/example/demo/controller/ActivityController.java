package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Activity;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/graphql")
@RestController
public class ActivityController {
	@Autowired
	private ActivityRepository activityRepository; 
    private GraphQLService graphQLService;
   
	public ActivityController(GraphQLService graphQLService) {
		this.graphQLService = graphQLService;
	}

	   @PostMapping
	    public ResponseEntity<Object> getAllBooks(@RequestBody String query){
	        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
	        System.out.println(execute.getData().toString());
	        return new ResponseEntity<>(execute, HttpStatus.OK);
	    }
	   
	   @RequestMapping("/")
	    public Activity index() {
	        return activityRepository.findById("5d567a185900201698018913").get();
	    }
	   

}
