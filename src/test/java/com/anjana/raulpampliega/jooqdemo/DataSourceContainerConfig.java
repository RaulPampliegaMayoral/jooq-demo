package com.anjana.raulpampliega.jooqdemo;

import java.util.Collections;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class DataSourceContainerConfig {

  private JdbcDatabaseContainer jdbcDatabaseContainer;

  @Value("spring.datasource.username")
  private String user;
  @Value("spring.datasource.password")
  private String password;

  private void initContainer() {
    jdbcDatabaseContainer = (JdbcDatabaseContainer) new PostgreSQLContainer("postgres:latest")
        .withUsername(user)
        .withPassword(password)
        .withDatabaseName("anjana")
        .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"));
    jdbcDatabaseContainer.start();
  }

  @Bean
  public DataSource getDataSource() {
    initContainer();

    DataSourceBuilder builder = DataSourceBuilder.create();
    String url = String.format("jdbc:postgresql://%s:%d/anjana", jdbcDatabaseContainer.getHost(),
        jdbcDatabaseContainer.getFirstMappedPort());

    builder.driverClassName("org.postgresql.Driver");
    builder.url(url);
    builder.username(user);
    builder.password(password);

    return builder.build();
  }
}
