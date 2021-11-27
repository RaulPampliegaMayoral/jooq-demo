package com.anjana.raulpampliega.jooqdemo;

import com.speedment.jpastreamer.application.JPAStreamer;
import javax.persistence.EntityManagerFactory;
import org.jinq.jpa.JPAQueryLogger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = {"com.anjana.raulpampliega.jooqdemo"})
@EntityScan(basePackages = {"com.anjana.raulpampliega.jooqdemo.model"})
@SpringBootApplication
public class JooqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JooqDemoApplication.class, args);
	}

	@Bean
	@Autowired
	JinqJPAStreamProvider jinqProvider(EntityManagerFactory emf) {
		JinqJPAStreamProvider streams = new JinqJPAStreamProvider(emf);
		streams.setHint("queryLogger",
				(JPAQueryLogger) (query, positionParameters, namedParameters) -> System.out.println("  " + query));
		return streams;
	}
}
