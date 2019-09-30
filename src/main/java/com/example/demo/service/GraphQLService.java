package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.demo.dataFetcher.QueryFetcher;
import com.example.demo.domain.Activity;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Service
public class GraphQLService {
	
	@Autowired
	private QueryFetcher queryDataFetcher;
   
	@Value("classpath:activity.graphql")
	Resource resource;
	private GraphQL graphQL;
	

    @PostConstruct
    private void loadSchema() throws IOException {
 
        File file = resource.getFile();
        //Parse Schema
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder
                		.dataFetcher("findActivity", queryDataFetcher)
                		)
                .type("Activity", typeWiring -> typeWiring
                        .dataFetcher("status", statusDataFetcher())
                )
                .build();
        
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
         graphQL = GraphQL.newGraphQL(graphQLSchema).build();

    }
    
    
    
    @Bean
    public GraphQL getGraphQL(){
        return graphQL;
    }
    
    public DataFetcher statusDataFetcher() {
        return dataFetchingEnvironment -> {
            Activity activity = dataFetchingEnvironment.getSource();
            return activity.getStatus();
        };
    }

}
