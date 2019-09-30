package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/graphql")
@RestController
public class ActivityController {
	
    private GraphQLService graphQLService;
   
	public ActivityController(GraphQLService graphQLService) {
		this.graphQLService = graphQLService;
	}

	   @PostMapping
	    public ResponseEntity<Object> getActivity(@RequestBody String query){
	        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
	        System.out.println(execute.getData().toString());
	        return new ResponseEntity<>(execute, HttpStatus.OK);
	    }
	   
	   

}
