package com.anjana.raulpampliega.jooqdemo.repositories;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;
import static org.hibernate.jpa.QueryHints.HINT_READONLY;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import java.util.stream.Stream;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface EntityRepository extends JpaRepository<Entity, Integer>,
    CustomEntityRepository {

  @Query("select e from Entity e")
  @QueryHints(value = {
      @QueryHint(name = HINT_FETCH_SIZE, value = "1000"),
      @QueryHint(name = HINT_CACHEABLE, value = "false"),
      @QueryHint(name = HINT_READONLY, value = "true")})
  Stream<Entity> findAllWithStream();
}
