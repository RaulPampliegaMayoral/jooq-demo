package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Integer> {

}
