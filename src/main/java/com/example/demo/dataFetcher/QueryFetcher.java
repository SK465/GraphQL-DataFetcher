package com.example.demo.dataFetcher;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Activity;
import com.example.demo.domain.Status;
import com.example.demo.repository.ActivityRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
@Component
public class QueryFetcher implements DataFetcher<Activity>{

	ActivityRepository activityRepository;

	public QueryFetcher(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}


	@Override
	public Activity get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");       
		return findActivity(id);
	}


	Activity findActivity(String id) {
		Optional<Activity> activityOptional = activityRepository.findById(id);
		if (!activityOptional.isPresent() || activityOptional.toString().isEmpty()) {
			System.out.println("inside if activity not found getActivityByID  :" + id);
		}
		return activityOptional.get();

	}

}
