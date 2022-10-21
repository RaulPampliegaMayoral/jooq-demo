package com.anjana.raulpampliega.jooqdemo;

import java.util.Collections;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class DataSourceContainerConfig {

  private JdbcDatabaseContainer jdbcDatabaseContainer;

  private final String databaseName = "anjana";

  @Bean
  @ConfigurationProperties("spring.datasource.mysql")
  public DataSourceProperties mysqlDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.postgresql")
  public DataSourceProperties postgresqlDataSourceProperties() {
    return new DataSourceProperties();
  }

  private DataSourceProperties initPostgresql() {
    DataSourceProperties properties = postgresqlDataSourceProperties();

    jdbcDatabaseContainer = (JdbcDatabaseContainer) new PostgreSQLContainer("postgres:latest")
        .withUsername(properties.getUsername())
        .withPassword(properties.getPassword())
        .withDatabaseName(databaseName)
        .withInitScript("schema/postgresql.schema.sql")
        .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"));
    jdbcDatabaseContainer.start();

    return properties;
  }

  private DataSourceProperties initMysql() {
    DataSourceProperties properties = mysqlDataSourceProperties();

    jdbcDatabaseContainer = new MySQLContainer<>("mysql:latest")
        .withUsername(properties.getUsername())
        .withPassword(properties.getPassword())
        .withDatabaseName(databaseName)
        .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"));
    jdbcDatabaseContainer.start();

    return properties;
  }

  @Bean
  public DataSource getDataSource() {
    DataSourceProperties properties = initPostgresql();

    return properties.initializeDataSourceBuilder()
        .url(jdbcDatabaseContainer.getJdbcUrl())
        .build();
  }
}
